package pororo.itemservice.web.basic;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pororo.itemservice.domain.item.Item;
import pororo.itemservice.domain.item.ItemRepository;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName, @RequestParam int price,
                       @RequestParam int quantity, Model model){
        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);
        return "basic/item";
    }

//    @PostMapping("/add")
    // @ModelAttribute 매개변수에 이름을 넣어주면 ModelAttribute가 자동으로 model에 해당 이름을 가지고 추가해줌
    public String addItemV2(@ModelAttribute("item") Item item){
        itemRepository.save(item);
        return "basic/item";
    }

//    @PostMapping("/add")
     // ModelAttribute에 매개변수를 생략하면 만들어진 객체 타입의 첫글자를 소문자로 바꿔 저장함.
    public String addItemV3(@ModelAttribute Item item){
        itemRepository.save(item);
        return "basic/item";
    }

//    @PostMapping("/add")
    // @ModelAttribute도 생략 가능
    public String addItemV4(Item item){
        itemRepository.save(item);
        return "basic/item";
    }

    // PRG 패턴 적용
//    @PostMapping("/add")
    public String addItemV5(Item item){
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        //RedirectAttributes에 넣어놓은 값들을 사용할 수 있음
        // 남은 값들은 url query로 넘어감
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);

        return "redirect:/basic/items/{itemId}";
    }


    /**
     * 테스트용 데이터
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }



}
