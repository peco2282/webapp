package com.github.peco2282.webapp.mapper

import com.github.peco2282.webapp.entity.User
import org.apache.ibatis.annotations.*

@Mapper
interface UserMapper : SqlMapper<User> {
  @Select("SELECT * FROM web_app.user")
  override fun selectAll(): List<User>

  @Select("SELECT * FROM web_app.user WHERE name = #{name}")
  fun selectByName(name: String): User?

  @Select("SELECT * FROM web_app.user WHERE id = #{id}")
  override fun selectById(id: String): User?

//  @Insert("INSERT INTO web_app.mapper(name, password, role) VALUE (#{name}, #{password}, #{role})")
//  fun addUser(name: String, password: String, role: Int)

  @Select("SELECT * FROM web_app.mapper WHERE role = #{role}")
  fun selectByRoleId(role: Int): List<User>

//  fun getUserInstance(name: String) = selectUser(name)?: User()

  @Insert("INSERT INTO web_app.user(name, password, role) VALUE (#{name}, #{password}, #{role})")
  fun addUser(user: User)

  @Delete("DELETE FROM web_app.user WHERE name = #{name}")
  fun deleteUser(name: String): Boolean

  @Update("UPDATE web_app.user SET name = #{after.name}, password = #{after.password}, role = #{role} WHERE name = #{name}")
  fun updateUser(name: String, after: User): Boolean
}