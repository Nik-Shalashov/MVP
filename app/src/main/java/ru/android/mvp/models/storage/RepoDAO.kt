package ru.android.mvp.models.storage

import androidx.room.*
import io.reactivex.rxjava3.core.Single

@Dao
interface RepoDAO {
    @Query("SELECT * FROM RoomGithubRepo WHERE userUrl = :reposUrl")
    fun findForUserRepo(reposUrl: String?): Single<List<RoomGithubRepo>>

    @Query("SELECT * FROM RoomGithubRepo WHERE url = :url")
    fun findRepo(url: String?): Single<RoomGithubRepo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repo: RoomGithubRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomGithubRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGithubRepo>)

    @Update
    fun update(user: RoomGithubRepo)

    @Update
    fun update(vararg users: RoomGithubRepo)

    @Update
    fun update(users: List<RoomGithubRepo>)

    @Delete
    fun delete(user: RoomGithubRepo)

    @Delete
    fun delete(vararg users: RoomGithubRepo)

    @Delete
    fun delete(users: List<RoomGithubRepo>)

    @Query("SELECT * FROM RoomGithubRepo")
    fun getAll(): Single<List<RoomGithubRepo>>

}