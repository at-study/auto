package redmine.utils.gson;

import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonHelper {

    /**
     * Получение экземляра Gson, умеющего правильно работать с LocalDateTime.
     *
     * @return gson
     */

    public static Gson getGson() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                .create();
        return gson;
    }

}
