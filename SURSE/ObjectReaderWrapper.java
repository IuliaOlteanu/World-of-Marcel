import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ObjectReaderWrapper {
    private static final String ACCOUNTS_JSON_PATH = "D:\\Facultate\\ANUL 2\\SEM1-Iulia\\Programare Orientata pe Obiecte\\tema\\accounts.json";
    private static final String STORIES_JSON_PATH = "D:\\Facultate\\ANUL 2\\SEM1-Iulia\\Programare Orientata pe Obiecte\\tema\\stories.json";
    private ObjectReaderWrapper() {

    }

    public static List<Account> readAccounts() throws IOException {
        File jsonFile = new File(ACCOUNTS_JSON_PATH);
        ObjectMapper mapper = getObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonFile).get("accounts");
        ObjectReader reader = mapper.readerFor(new TypeReference<List<Account>>() {
        });
        return reader.readValue(jsonNode);

    }

    public static Map<celula, List<String>> readStories() throws IOException {
        File jsonFile = new File(STORIES_JSON_PATH);
        ObjectMapper mapper = getObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonFile).get("stories");
        ObjectReader reader = mapper.readerFor(new TypeReference<List<Story>>() {
        });
        List<Story> stories = reader.readValue(jsonNode);

        return convertStoriesToMap(stories);
    }

    private static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return mapper;
    }

    private static Map<celula, List<String>> convertStoriesToMap(List<Story> stories) {
        EnumMap<celula, List<String>> storiesMap = new EnumMap<>(celula.class);

        for (celula cellEnum : celula.values()) {
            storiesMap.put(cellEnum, stories.stream()
                    .filter(story -> cellEnum == story.getType())
                    .map(Story::getValue).collect(Collectors.toList()));
        }
        return storiesMap;
    }
}
