package com.epam.izh.rd.online.repository;
import com.epam.izh.rd.online.entity.SchoolBook;
import java.util.Arrays;
import java.util.Objects;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook [] schoolBooks = new SchoolBook[0];
    /**
     * Метод должен сохранять школьную книгу в массив schoolBooks.
     * Массив при каждом сохранении должен увеличиваться в размере ровно на 1.
     * То есть он ровно того размера, сколько сущностей мы в него сохранили.
     * <p>
     * Одну и ту же книгу МОЖНО сохранить в массиве несколько раз, проверки на то, что такая книга уже сохранена, делать не нужно.
     * <p>
     * Если сохранение прошло успешно, метод должен вернуть true.
     *
     * @param book
     */
    @Override
    public boolean save(SchoolBook book) {
        if (schoolBooks.length == 0){
            schoolBooks = new SchoolBook[]{book};
        }
        else{
            int indexBook = schoolBooks.length  +  1;
            schoolBooks = Arrays.copyOf(schoolBooks,indexBook);
            schoolBooks[indexBook - 1 ] = book;
        }
        return true;
    }


    /**
     * Метод должен находить в массиве schoolBooks все книги по имени.
     * <p>
     * Если книги найдены - метод должен их вернуть.
     * Если найденных по имени книг нет, должен вернуться пустой массив.
     *
     * @param name
     */
    @Override
    public SchoolBook[] findByName(String name) {
        int indexBook = 0;
        for (SchoolBook i : schoolBooks){
            if (schoolBooks.equals(i.getName())){
                indexBook++;
            }
        }
        SchoolBook[] findBooks = new SchoolBook[indexBook + 1];
        for (int i = 0, j = 0; i< schoolBooks.length; i++){
            if (name.equals(schoolBooks[i].getName())){
                findBooks[j] = schoolBooks[i];
                j++;
            }
        }
        return findBooks;
    }
    /**
     * Метод должен удалять книги из массива schoolBooks по названию.
     * Если книг с одинаковым названием в массиве несколько, метод должен удалить их все.
     * <p>
     * Важно: при удалении книги из массива размер массива должен уменьшиться!
     * То есть, если мы сохранили 2 разные книги и вызвали count() (метод ниже), то он должен вернуть 2.
     * Если после этого мы удалили 1 книгу, метод count() должен вернуть 1.
     * <p>
     * Если хотя бы одна книга была найдена и удалена, метод должен вернуть true, в противном случае,
     * если книга не была найдена, метод должен вернуть false.
     *
     * @param name
     */
    @Override
    public boolean removeByName(String name) {
        while (findByName(name).length>0) {
           int indexOfDelete = 0;
           for (int i = 0;i<schoolBooks.length;i++){
               if (name.equals(schoolBooks[i].getName())){
                   indexOfDelete =i;
                   break;
               }
           }
           for(int i = indexOfDelete;i<schoolBooks.length - 1;i++){
               schoolBooks[i] = schoolBooks[i+1];
           }
           schoolBooks = Arrays.copyOf(schoolBooks,schoolBooks.length-1);
           if (findByName(name).length ==0){
               return true;
           }
       }
        return false;
    }

    /**
     * Метод возвращает количество сохраненных сущностей в массиве schoolBooks.
     */
    @Override
    public int count() {
        return schoolBooks.length;
    }
}

