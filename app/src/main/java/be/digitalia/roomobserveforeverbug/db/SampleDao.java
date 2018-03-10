package be.digitalia.roomobserveforeverbug.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface SampleDao {

	@Insert
	void insert(DummyEntity entity);

	@Query("SELECT COUNT(*) from DummyEntity")
	LiveData<Integer> getItemsCount();
}
