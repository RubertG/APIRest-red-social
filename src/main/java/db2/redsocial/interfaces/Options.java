package db2.redsocial.interfaces;

import java.util.List;
import java.util.Optional;

public interface Options<T, E> {

    public List<T> consult();

    public Boolean add(T myObject);

    public Long recordsLenght();

    public Boolean delete(E id);

    public Boolean update(T myObject);

    public Optional<T> search(E primaryKey);
}
