package database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dao.ExcursionDao;
import dao.VacationDao;
import entities.Excursion;
import entities.Vacation;

public class Repository {

    private VacationDao vacationDao;
    private ExcursionDao excursionDao;
    private List<Vacation> vacationList;
    private List<Excursion> excursionList;
    private List<Vacation> vacationListById;
    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        TripDatabaseBuilder db = TripDatabaseBuilder.getDatabase(application);
        vacationDao = db.VacationDao();
        excursionDao = db.ExcursionDao();
    }

    public List<Vacation> getVacations() {
        databaseExecutor.execute(() -> {
            vacationList = vacationDao.getAllVacations();
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return vacationList;
    }

    // Search Functionality
    public List<Vacation> getSearchVacations(String query) {
        databaseExecutor.execute(() -> {
            vacationList = vacationDao.getVacationsByName(query);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return vacationList;
    }

    public List<Vacation> getVacationById() {
        databaseExecutor.execute(() -> {
            vacationListById = vacationDao.getAllVacationsById();
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return vacationListById;
    }

    public void insert(Vacation vacation) {
        databaseExecutor.execute(() -> {
            vacationDao.insert(vacation);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Vacation vacation) {
        databaseExecutor.execute(() -> {
            vacationDao.update(vacation);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Vacation vacation) {
        databaseExecutor.execute(() -> {
            vacationDao.delete(vacation);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Excursion> getAllExcursions() {
        databaseExecutor.execute(() -> {
            excursionList = excursionDao.getAllExcursions();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return excursionList;
    }

    public void insert(Excursion excursion) {
        databaseExecutor.execute(() -> {
            excursionDao.insert(excursion);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Excursion excursion) {
        databaseExecutor.execute(() -> {
            excursionDao.update(excursion);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Excursion excursion) {
        databaseExecutor.execute(() -> {
            excursionDao.delete(excursion);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
