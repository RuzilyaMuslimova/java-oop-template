package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;

/**
 * Интерфейс сервиса для выполнения бизнес логики при работе с книга и авторами и взаимодействием с
 * репозиторием для книг BookRepository и сервисом для авторов AuthorService.
 * <p>
 * Необходимо:
 * 1) Создать в этом же пакете класс SimpleSchoolBookService
 * 2) Имплементировать им данный интерфейс
 * 3) Добавить все методы (пока можно не писать реализацию)
 * 4) Добавить в SimpleSchoolBookService приватное поле "BookRepository<SchoolBook> schoolBookBookRepository" - это репозиторий
 * книг к которому вы будете обращаться в методах
 * 5) Добавить в SimpleSchoolBookService приватное поле "AuthorService authorService" - это сервис для работы с авторами к которому
 * вы будете обращаться в методах
 * 6) Создать дефолтный конструтор (без параметров)
 * 7) Создать конструтор с параметрами "BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService"
 * (который будет устанвливать в поле schoolBookBookRepository и в поле authorService значения)
 * 8) Написать в классе SimpleSchoolBookService реализацию для всех методов
 */
public interface BookService<T extends Book> {

    /**
     * Метод должен сохранять книгу.
     *
     * Перед сохранением книги нужно проверить, сохранен ли такой автор в базе авторов.
     * То есть вы должен взять имя и фамилию автора из книги и обратиться к сервису авторов и узнать о наличии такого автора.
     * Напомню, что мы считаем, что двух авторов с одинаковыми именем и фамилией быть не может.
     *
     * Если такой автор сущесвует (сохранен) - значит можно сохранять и книгу.
     * Если же такого автора в базе нет, значит книгу сохранять нельзя.
     *
     * Соответственно, если книга была успешно сохранена - метод возвращает true, если же книга не была сохранена - метод возвращает false.
     */
    boolean save(SchoolBook book);

    /**
     * Метод должен находить книгу по имени.
     * <p>
     * По факту, он просто обращается к репозиторию с книгами и вызывает аналогичный метод, псоле чего возвращает результат.
     */
    T[] findByName(String name);

    /**
     * Метод должен находить количество сохраненных книг по конкретному имени книги.
     */
    int getNumberOfBooksByName(String name);

    /**
     * Метод должен удалять все книги по имени.
     * <p>
     * По факту, он просто обращается к репозиторию с книгами и вызывает аналогичный метод, псоле чего возвращает результат.
     */
    boolean removeByName(String name);

    /**
     * Метод должен возвращать количество всех книг.
     * <p>
     * По факту, он просто обращается к репозиторию с книгами и вызывает аналогичный метод, псоле чего возвращает результат.
     */
    int count();

    /**
     * Метод должен возвращать автора книги по названию книги.
     *
     * То есть приждется сходить и в репозиторий с книгами и в сервис авторов.
     *
     * Если такой книги не найдено, метод должен вернуть null.
     */
    Author findAuthorByBookName(String name);
}
