import json
import requests
import numpy
import matplotlib
import string, time
import os
from Sastrawi.StopWordRemover.StopWordRemoverFactory import StopWordRemoverFactory
from Sastrawi.Stemmer.StemmerFactory import StemmerFactory

import tensorflow as tf
import tensorflow_hub as hub

SIZE = 128
MODEL_URI = 'http://localhost:8501/v1/models/pets:predict'

custom_objects = {'KerasLayer':hub.KerasLayer,'tf': tf}
ext_model = tf.keras.models.load_model('./ext_model.h5',custom_objects=custom_objects)
neu_model = tf.keras.models.load_model('./neu_model.h5',custom_objects=custom_objects)
agr_model = tf.keras.models.load_model('./agr_model.h5',custom_objects=custom_objects)
con_model = tf.keras.models.load_model('./con_model.h5',custom_objects=custom_objects)
opn_model = tf.keras.models.load_model('./opn_model.h5',custom_objects=custom_objects)

def preprocess_text(input):
  #lowercase all character in the text
  text = input[0]
  text = text.lower()
  #remove punctuation
  text = text.translate(str.maketrans("","",string.punctuation))
  #remove leading and trailing whitespace
  text = text.strip()
  #remove StopWord
  stopword = StopWordRemoverFactory().create_stop_word_remover()
  text = stopword.remove(text)
  #stemming
  stemmer = StemmerFactory().create_stemmer()
  text = stemmer.stem(text)
  return text

def predict(data):
    start_time = time.time()

    data = preprocess_text(data)
    ext_prediction = ext_model.predict(data)
    neu_prediction = neu_model.predict(data)
    agr_prediction = agr_model.predict(data)
    con_prediction = con_model.predict(data)
    opn_prediction = opn_model.predict(data)

   # prediction = ext_model.predict(data.get("instances"))
    ext_prediction_string = [str(pred) for pred in ext_prediction]
    neu_prediction_string = [str(pred) for pred in neu_prediction]
    agr_prediction_string = [str(pred) for pred in agr_prediction]
    con_prediction_string = [str(pred) for pred in con_prediction]
    opn_prediction_string = [str(pred) for pred in opn_prediction]
    response_json = {
        "data" : data,
        #"data" : data.get("instances"),
        "ext_prediction": list(ext_prediction_string),
        "neu_prediction": list(neu_prediction_string),
        "agr_prediction": list(agr_prediction_string),
        "con_prediction": list(con_prediction_string),
        "opn_prediction": list(opn_prediction_string),
        "time_consumed": str(time.time()-start_time)
    }

    return json.dumps(response_json)
