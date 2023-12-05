package cat.institutmarianao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cat.institutmarianao.service.MovimentStockService;

@Controller
public class MovimentStockController {

    @Autowired
    private MovimentStockService movimentStockService;

    @RequestMapping("/movimentestoc/{id}/{qty}/{num}")
    public String process(@PathVariable("id") String id,@PathVariable("qty") int qty,@PathVariable("num") int num) {
        movimentStockService.processMovimentStock(id, qty, num);
        return "redirect:/medicaments";
    }
}
