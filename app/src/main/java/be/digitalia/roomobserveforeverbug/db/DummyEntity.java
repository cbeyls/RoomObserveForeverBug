package be.digitalia.roomobserveforeverbug.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class DummyEntity {

	@PrimaryKey(autoGenerate = true)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
