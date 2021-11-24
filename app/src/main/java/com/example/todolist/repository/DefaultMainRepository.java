package com.example.todolist.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.todolist.db.Db;
import com.example.todolist.model.Note;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DefaultMainRepository implements MainRepository{

    @Override
    public void addNote(Note note) {
        Observable<Note> observable;
        observable = io.reactivex.Observable.just(note);
        observable.subscribeOn( Schedulers.io() )
                .subscribe( new Observer<Note>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) { }
                    @Override
                    public void onNext(Note note) {
                        Db.getInstance().noteDao().insertNote(note);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                } );
    }

    @Override
    public void deleteNote(Note note) {
        Observable<Note> observable;
        observable = io.reactivex.Observable.just(note);
        observable.subscribeOn( Schedulers.io() )
                .subscribe( new Observer<Note>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(Note note) {
                        Db.getInstance().noteDao().deleteNote(note);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                } );
    }

    @Override
    public void updateNote(Note note) {
        Observable<Note> observable;
        observable = io.reactivex.Observable.just(note);
        observable.subscribeOn( Schedulers.io() )
                .subscribe( new Observer<Note>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(Note note) {
                        Db.getInstance().noteDao().updateNote(note);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                } );
    }

    @Override
    public LiveData<List<Note>> getAllNotes() {
        return Db.getInstance().noteDao().getAllNotes();
    }
}
