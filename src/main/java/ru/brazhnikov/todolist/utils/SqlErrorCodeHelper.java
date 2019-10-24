package ru.brazhnikov.todolist.utils;

import java.util.HashMap;

/**
 * SqlErrorCodeHelper - класс с набором ошибок и описаний нестандартных исключений
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.utils
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
public class SqlErrorCodeHelper {

    /**
     * @access private
     * @var HashMap<String, String> errorCodes - список сообщений (ключ - код / значение - сообщений)
     */
    private HashMap<String, String> errorCodes = new HashMap<>();

    /**
     * SqlErrorCodeHelper - конструктор
     */
    public SqlErrorCodeHelper() {
        this.errorCodes.put( "23505", "Значение поля должно быть уникальным." );
    }

    /**
     * getErrorCodes - получить список кодов и сообщений
     * @param key
     * @return String
     */
    public String  getErrorCodes( String key ) {
        return errorCodes.get( key );
    }
}
