package vn.edu.fpt.pru.service;

import vn.edu.fpt.pru.model.WordDTO;
import vn.edu.fpt.pru.model.WordEnum;

public interface IWordService {

    WordDTO getWord(WordEnum number);

    void changeStatus(Integer id);

    void resetData();
}
