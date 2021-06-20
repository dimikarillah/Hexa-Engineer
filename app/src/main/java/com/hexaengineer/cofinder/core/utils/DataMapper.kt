package com.hexaengineer.cofinder.core.utils

import com.hexaengineer.cofinder.core.data.source.local.entity.UserDetailEntity
import com.hexaengineer.cofinder.core.data.source.local.entity.UserEntity
import com.hexaengineer.cofinder.core.data.source.remote.response.DataItem
import com.hexaengineer.cofinder.core.data.source.remote.response.UserItem
import com.hexaengineer.cofinder.core.domain.model.DetailUser
import com.hexaengineer.cofinder.core.domain.model.User

object DataMapper {
    fun mapUserResponsesToEntities(input: List<UserItem>): List<UserEntity> {
        val userList = ArrayList<UserEntity>()
        input.map {
            val user = UserEntity(
                id = it.id,
                name = it.name,
                skills = it.skills,
                picture = it.picture,
                city = it.city
            )
            userList.add(user)
        }
        return userList
    }

    fun mapUserEntitiesToDomain(input: List<UserEntity>): List<User> =
        input.map {
            User(
                id = it.id,
                name = it.name,
                skills = it.skills,
                picture = it.picture,
                city = it.city
            )
        }

    fun mapDetailUserResponsesToEntities(input: List<DataItem>): List<UserDetailEntity> {
        val userDetail = ArrayList<UserDetailEntity>()
        input.map {
            val user = UserDetailEntity(
                id = it.id,
                personalities = it.personalities,
                description = it.description,
                address = it.address,
                kontak = it.kontak
            )
            userDetail.add(user)
        }
        return userDetail
    }

    fun mapDetailUserEntitiesToDomain(input: List<UserDetailEntity>): List<DetailUser> =
        input.map {
            DetailUser(
                id = it.id,
                personalities = it.personalities,
                description = it.description,
                address = it.address,
                kontak = it.kontak
            )
        }

    fun mapDetailUserResponsesToDomain(input: List<DataItem>): List<DetailUser> {
        val userDetail = ArrayList<DetailUser>()
        input.map {
            val user = DetailUser(
                id = it.id,
                personalities = it.personalities,
                description = it.description,
                address = it.address,
                kontak = it.kontak
            )
            userDetail.add(user)
        }
        return userDetail
    }
}