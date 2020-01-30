package com.yakovlev.BankSystem.dao;

import com.yakovlev.BankSystem.model.Worker;

import java.util.Collection;

public interface WorkerDao {

    Collection<Worker> getAll();

    Worker getWorkerById(long workerId);

    void saveWorker(Worker worker);

    void editWorker(Worker worker);

    void deleteWorker(Worker worker);
}
