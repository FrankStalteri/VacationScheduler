package database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import dao.ExcursionDao;
import dao.VacationDao;
import entities.Excursion;
import entities.Vacation;

@Database(entities = {Vacation.class, Excursion.class}, version = 4, exportSchema = false)
public abstract class TripDatabaseBuilder extends RoomDatabase {

    public abstract VacationDao VacationDao();
    public abstract ExcursionDao ExcursionDao();

    private static volatile TripDatabaseBuilder INSTANCE;

    static TripDatabaseBuilder getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (TripDatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TripDatabaseBuilder.class, "MyTripDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
