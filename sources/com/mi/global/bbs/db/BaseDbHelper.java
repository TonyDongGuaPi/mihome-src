package com.mi.global.bbs.db;

import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

public class BaseDbHelper<T, K> {
    private AbstractDao<T, K> mDao;

    public BaseDbHelper(AbstractDao abstractDao) {
        this.mDao = abstractDao;
    }

    public void save(T t) {
        this.mDao.insert(t);
    }

    public void save(T... tArr) {
        this.mDao.insertInTx(tArr);
    }

    public void save(List<T> list) {
        this.mDao.insertInTx(list);
    }

    public void saveOrUpdate(T t) {
        this.mDao.insertOrReplace(t);
    }

    public void saveOrUpdate(T... tArr) {
        this.mDao.insertOrReplaceInTx(tArr);
    }

    public void saveOrUpdate(List<T> list) {
        this.mDao.insertOrReplaceInTx(list);
    }

    public void deleteByKey(K k) {
        this.mDao.deleteByKey(k);
    }

    public void delete(T t) {
        this.mDao.delete(t);
    }

    public void delete(T... tArr) {
        this.mDao.deleteInTx(tArr);
    }

    public void delete(List<T> list) {
        this.mDao.deleteInTx(list);
    }

    public void deleteAll() {
        this.mDao.deleteAll();
    }

    public void update(T t) {
        this.mDao.update(t);
    }

    public void update(T... tArr) {
        this.mDao.updateInTx(tArr);
    }

    public void update(List<T> list) {
        this.mDao.updateInTx(list);
    }

    public T query(K k) {
        return this.mDao.load(k);
    }

    public List<T> queryAll() {
        return this.mDao.loadAll();
    }

    public List<T> query(String str, String... strArr) {
        return this.mDao.queryRaw(str, strArr);
    }

    public QueryBuilder<T> queryBuilder() {
        return this.mDao.queryBuilder();
    }

    public long count() {
        return this.mDao.count();
    }

    public void refresh(T t) {
        this.mDao.refresh(t);
    }

    public void detach(T t) {
        this.mDao.detach(t);
    }
}
