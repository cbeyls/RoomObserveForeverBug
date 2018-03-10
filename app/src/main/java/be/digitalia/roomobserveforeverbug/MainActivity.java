package be.digitalia.roomobserveforeverbug;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import be.digitalia.roomobserveforeverbug.db.DummyEntity;
import be.digitalia.roomobserveforeverbug.db.SampleDatabase;

public class MainActivity extends AppCompatActivity {

	private TextView activityLifecycleCounter;
	private TextView observeForeverCounter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new InsertNewItemTask(MainActivity.this).execute();
			}
		});

		activityLifecycleCounter = findViewById(R.id.counter_display_activity_lifecycle);
		observeForeverCounter = findViewById(R.id.counter_display_observe_forever);

		SampleDatabase.getInstance(this).sampleDao().getItemsCount().observe(this, new Observer<Integer>() {
			@Override
			public void onChanged(@Nullable Integer counter) {
				activityLifecycleCounter.setText(String.valueOf(counter));
			}
		});

		// Note: this observer will leak the Activity.
		// In a real-world example, the observer would be declared inside a standalone application component.
		SampleDatabase.getInstance(this).sampleDao().getItemsCount().observeForever(new Observer<Integer>() {
			@Override
			public void onChanged(@Nullable Integer counter) {
				observeForeverCounter.setText(String.valueOf(counter));
			}
		});
	}

	private static class InsertNewItemTask extends AsyncTask<Void, Void, Void> {

		@SuppressLint("StaticFieldLeak")
		private final Context appContext;

		public InsertNewItemTask(Context context) {
			appContext = context.getApplicationContext();
		}

		@Override
		protected Void doInBackground(Void... voids) {
			SampleDatabase.getInstance(appContext)
					.sampleDao()
					.insert(new DummyEntity());
			return null;
		}
	}
}
