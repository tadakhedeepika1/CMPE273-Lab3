package edu.sjsu.cmpe.cache.repository;

import java.util.List;

import edu.sjsu.cmpe.cache.domain.Entry;

/**
 * Entry repository interface.
 * 
 * What is repository pattern?
 * 
 * @see http://martinfowler.com/eaaCatalog/repository.html
 */
public interface Cache {
    /**
     * Save a new entry in the repository
     * 
     * @param newentry
     *            a entry instance to be create in the repository
     * @return an entry instance
     */
    Entry saveEntry(Entry newEntry);

    /**
     * Retrieve an existing entry by key
     * 
     * @param key
     *            a valid key
     * @return a entry instance
     */
    Entry get(int key);

    /**
     * Retrieve all entries
     * 
     * @return a list of entries
     */
    List<Entry> getAll();

}
