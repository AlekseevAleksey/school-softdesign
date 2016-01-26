package com.softdesign.school.utils;

import android.util.Log;

/**
 *
 * Зарефакторить код логера в соответствии с данными на лекции рекомендациями, исспользовать подход DRY Don’t repeat yourself (не повторяй себя) - 
 * т.е. избегаем повторения уже ранее написанного кода + Javadoc, 
 * логер должен исспользовать различные уровни вывода логов (Verbose, debug, info, error, warn, assert ).
 *
 */

/**
 * Является оберткой для {@link android.util.Log}, занимается записью сообщений различного
 * характера в лог.
 * */
public class Lg {

    /**
     * Префикс, добавляемый к тегу сообщения, записываемого в лог.
     */
    private static final String PREFIX = "SoftDesign";

    /**
     * Максимальный размер строки, которая может быть записана в лог целиком. Более длинные строки
     * будут обрезаться и выводиться на следующей строке.
     */
    public static final int LOGCAT_BUFFER_SIZE = 3000;


    /**
     * Проверяет включено ли логгирование
     */
    private static boolean shouldLog() {
//        return BuildConfig.IS_LOGCAT_LOGGER_ENABLED;
        return true;
    }

    /**
     * отправляет сообщение в лог с уровнем VERBOSE
     * @param tag идентификатор источника сообщения
     * @param msg сообщение которое нужно записать
     */
    public static void v(String tag, String msg) {
        logMsg(android.util.Log.VERBOSE, tag, msg);
    }

    /**
     * отправляет сообщение в лог с уровнем DEBUG
     * @param tag идентификатор источника сообщения
     * @param msg сообщение которое нужно записать
     */
    public static void d(String tag, String msg) {
        logMsg(android.util.Log.DEBUG, tag, msg);
    }

    /**
     *отправляет сообщение в лог с уровнем INFO
     * @param tag идентификатор источника сообщения
     * @param msg сообщение которое нужно записать
     */
    public static void i(String tag, String msg) {
        logMsg(android.util.Log.INFO, tag, msg);
    }

    /**
     * отправляет сообщение в лог с уровнем WARN
     * @param tag идентификатор источника сообщения
     * @param msg сообщение которое нужно записать
     */
    public static void w(String tag, String msg) {
        logMsg(android.util.Log.WARN, tag, msg);
    }

    /**
     * отправляет сообщение в лог с уровнем ERROR
     * @param tag идентификатор источника сообщения
     * @param msg сообщение которое нужно записать
     */
    public static void e(String tag, String msg) {
        logMsg(android.util.Log.ERROR, tag, msg);
    }

    /**
     * отправляет сообщение в лог с уровнем ASSERT
     * @param tag идентификатор источника сообщения
     * @param msg сообщение которое нужно записать
     */
    public static void a(String tag, String msg) {
        logMsg(android.util.Log.ASSERT, tag, msg);
    }

    /**
     *
     * @param level константа указывающая на уровень логгируемого сообщения
     * @param tag идентивфикатор источника сообщения
     * @param msg сообщение которое нужно записать
     */
    private static void logMsg (int level, String tag, String msg){
        if (shouldLog()) {
            String str = msg;
            while (str.length() > LOGCAT_BUFFER_SIZE){
                String substr = str.substring(0, LOGCAT_BUFFER_SIZE);
                str = substr.substring(LOGCAT_BUFFER_SIZE);
                Log.println(level, PREFIX + tag, str);
            }
            Log.println(level, PREFIX + tag, str);
        }
    }
}
