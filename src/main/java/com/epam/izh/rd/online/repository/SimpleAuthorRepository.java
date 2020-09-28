package com.epam.izh.rd.online.repository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.epam.izh.rd.online.entity.Author;


public  class SimpleAuthorRepository implements AuthorRepository{
    private Author[] authors = new Author[0];
    /**
     * Метод должен сохранять автора в массив authors.
     * Массив при каждом сохранении должен увеличиваться в размере ровно на 1.
     * То есть он ровно того размера, сколько сущностей мы в него сохранили.
     * <p>
     * Если на вход для сохранения приходит автор, который уже есть в массиве (сохранен), то автор не сохраняется и
     * метод возвращает false.
     * <p>
     * Можно сравнивать только по полному имени (имя и фамилия), считаем, что двух авторов
     * с одинаковыми именем и фамилией быть не может.
     * Подсказка - можно использовать для проверки метод findByFullName.
     * <p>
     * Если сохранение прошло успешно, метод должен вернуть true.
     *
     * @param author
     */
    @Override
    public boolean save(Author author) {
        if (authors.length == 0){
            authors = new Author[]{author};
            return true;
        }
         if (findByFullName(author.getName(),author.getLastName())== null){
            int indexAuthor = authors.length + 1;
            authors = Arrays.copyOf(authors,indexAuthor);
            authors[indexAuthor] = author;
            return true;
        }
        return false;
    }

    /**
     * Метод должен находить в массиве authors автора по имени и фамилии (считаем, что двух авторов
     * с одинаковыми именем и фамилией быть не может.)
     * <p>
     * Если автор с таким именем и фамилией найден - возвращаем его, если же не найден, метод должен вернуть null.
     *
     * @param name
     * @param lastName
     */
    @Override
    public Author findByFullName(String name, String lastName) {
        for (Author i :authors){
            if (name.equals(i.getName()) && lastName.equals(i.getLastName())){
                return i;
            }
        }
        return null;
    }
    /**
     * Метод должен удалять автора из массива authors, если он там имеется.
     * Автора опять же, можно определять только по совпадению имении и фамилии.
     * <p>
     * Важно: при удалении автора из массива размер массива должен уменьшиться!
     * То есть, если мы сохранили 2 авторов и вызвали count() (метод ниже), то он должен вернуть 2.
     * Если после этого мы удалили 1 автора, метод count() должен вернуть 1.
     * <p>
     * Если автор был найден и удален, метод должен вернуть true, в противном случае, если автор не был найден, метод
     * должен вернуть false.
     *
     * @param author
     * @return
     */
    @Override
    public boolean remove(Author author) {
        Author [] deleteAuthors;
        if (findByFullName(author.getName(),author.getLastName())== null){
            return false;
        }else {
            deleteAuthors = new Author[count()-1];
            int j = 0;
            for (Author authorsArray : authors){
                if (authorsArray !=findByFullName(author.getName(),author.getLastName())){
                    deleteAuthors[j] = authorsArray;
                    j++;
                }
            }
        }
        authors = deleteAuthors;
        return true;
    }
    /**
     * Метод возвращает количество сохраненных сущностей в массиве authors.
     */
    @Override
    public int count() {
        return authors.length;
    }
}
