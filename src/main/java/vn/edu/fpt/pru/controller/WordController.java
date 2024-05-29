package vn.edu.fpt.pru.controller;

import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.pru.model.WordDTO;
import vn.edu.fpt.pru.model.WordEnum;
import vn.edu.fpt.pru.service.IWordService;

@RestController
@RequestMapping("/api/pru/word")
public class WordController {

    private final IWordService wordService;

    public WordController(IWordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/{number}")
    public WordDTO getWord(@PathVariable WordEnum number) {
        return wordService.getWord(number);
    }

    @PostMapping("/{id}")
    public void changeStatus(@PathVariable Integer id){
        wordService.changeStatus(id);
    }

    @GetMapping("/reset")
    public void resetData(){
        wordService.resetData();
    }
}
