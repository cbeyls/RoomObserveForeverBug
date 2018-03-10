package be.digitalia.roomobserveforeverbug.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {DummyEntity.class}, version = 1, exportSchema = false)
public abstract class SampleDatabase extends RoomDatabase {
	private static SampleDatabase instance;

	public abstract SampleDao sampleDao();

	public static synchronized SampleDatabase getInstance(Context context) {
		if (instance == null) {
			instance = Room.databaseBuilder(context.getApplicationContext(), SampleDatabase.class, "counter-db")
					.fallbackToDestructiveMigration()
					.build();
		}
		return instance;
	}
}
