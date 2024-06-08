package vn.edu.fpt.pru.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import vn.edu.fpt.pru.model.WordDTO;
import vn.edu.fpt.pru.model.WordEnum;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;


@Service
public class WordService implements IWordService {

    private static final String INIT_PATH = "src/main/resources/init.json";
    private static final String DATA_PATH = "src/main/resources/data.json";
    private static final String THREE = "three";
    private static final String FOUR = "four";

    @Override
    public WordDTO getWord(WordEnum number) {
        ObjectMapper objectMapper = new ObjectMapper();
        WordDTO result = new WordDTO();
        try {
            Map<String, Object> map = objectMapper.readValue(new File(DATA_PATH), new TypeReference<Map<String, Object>>() {
            });

            result = switch (number) {
                case THREE -> objectMapper
                        .convertValue(map.get(THREE), new TypeReference<List<WordDTO>>() {
                        })
                        .stream()
                        .filter(o -> !o.isStatus())
                        .findFirst()
                        .orElse(new WordDTO(-1, "Finish", true));
                case FOUR -> objectMapper
                        .convertValue(map.get(FOUR), new TypeReference<List<WordDTO>>() {
                        })
                        .stream()
                        .filter(o -> !o.isStatus())
                        .findFirst()
                        .orElse(new WordDTO(-1, "Finish", true));
                default -> null;
            };
        } catch (Exception e) {
            //swallow
        }
        return result;
    }

    @Override
    public void changeStatus(Integer id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, Object> map = objectMapper.readValue(new File(DATA_PATH), new TypeReference<Map<String, Object>>() {
            });

            List<WordDTO> three = objectMapper.convertValue(map.get(THREE), new TypeReference<>() {
            });
            for (WordDTO dto : three) {
                if (dto.getId().equals(id)) {
                    dto.setStatus(true);
                    map.put("current", id);
                }
            }
            List<WordDTO> four = objectMapper.convertValue(map.get(FOUR), new TypeReference<>() {
            });
            for (WordDTO dto : four) {
                if (dto.getId().equals(id)) {
                    dto.setStatus(true);
                    map.put("current", id);
                }
            }
            map.put(THREE, three);
            map.put(FOUR, four);

            File file = new File(new ClassPathResource(DATA_PATH).getPath());
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, map);
        } catch (Exception e) {
            //swallow
        }
    }

    @Override
    public void resetData() {
        try {
            Files.copy(Path.of(INIT_PATH), Path.of(DATA_PATH), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
