package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HumanDao {

        @Query("SELECT * FROM human")
        List<Human> getAll();

        @Query("SELECT * FROM human WHERE id = :id")
        Human getById(long id);

        @Insert
        void insert(Human human);

        @Update
        void update(Human human);

        @Delete
        void delete(Human human);
}
