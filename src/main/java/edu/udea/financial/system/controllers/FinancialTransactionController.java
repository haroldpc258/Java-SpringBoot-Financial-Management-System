package edu.udea.financial.system.controllers;

import edu.udea.financial.system.entities.FinancialTransaction;
import edu.udea.financial.system.services.CompanyService;
import edu.udea.financial.system.services.FinancialTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
@RequestMapping("/enterprises/{companyId}/movements")
public class FinancialTransactionController {

    @Autowired
    private FinancialTransactionService transactionService;
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public String getFinancialTransactions(Model model, @PathVariable Long companyId) {
        model.addAttribute("companyId", companyId);
        model.addAttribute("transactions", transactionService.getFinancialTransactions(companyId));
        return "transactionsView";
    }
    @GetMapping("/create")
    public String createFinancialTransaction(@PathVariable Long companyId, Model model) {
        model.addAttribute("companyId", companyId);
        model.addAttribute("newTransaction", new FinancialTransaction());
        return "createTransactionView";
    }

    @PostMapping("/create")
    public RedirectView createFinancialTransaction(@PathVariable Long companyId, @ModelAttribute FinancialTransaction transaction) {
        transactionService.createFinancialTransaction(companyId, transaction);
        return new RedirectView("/enterprises/" + companyId + "/movements");
    }

    @GetMapping("/{id}/update")
    public String updateFinancialTransaction(@PathVariable Long companyId, @PathVariable Long id, Model model) {
        model.addAttribute("companyId", companyId);
        model.addAttribute("transaction", transactionService.getTransactionById(id));
        return "updateTransactionView";
    }

    @PostMapping("/{id}/update")
    public RedirectView updateFinancialTransaction(@PathVariable Long companyId, @ModelAttribute FinancialTransaction transaction) {
        transactionService.updateTransaction(transaction);
        return new RedirectView("/enterprises/" + companyId + "/movements");
    }

    @DeleteMapping("/{id}")
    public RedirectView deleteTransactionById(@PathVariable Long companyId,  @PathVariable Long id) {
        transactionService.deleteFinancialTransactionById(companyId, id);
        return new RedirectView("/enterprises/" + companyId + "/movements");
    }

    /*@GetMapping
    public ResponseEntity<?> getFinancialTransactions(@PathVariable Long companyId) {
        if (companyService.companyExists(companyId)) {
            return new ResponseEntity<>(transactionService.getFinancialTransactions(companyId), HttpStatus.OK);
        }
        return companyNotFound(companyId);
    }*/

    /*@PostMapping
    public ResponseEntity<?> createFinancialTransaction(@PathVariable Long companyId, @RequestBody FinancialTransaction transaction) {
        if (companyService.companyExists(companyId)) {
            return new ResponseEntity<>(transactionService.createFinancialTransaction(companyId, transaction), HttpStatus.OK);
        }
        return companyNotFound(companyId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable Long companyId, @PathVariable Long id) {
        if (companyService.companyExists(companyId)) {
            if (transactionService.transactionExists(companyId, id)) {
                return new ResponseEntity<>(transactionService.getTransactionById(id), HttpStatus.OK);
            }
            return transactionNotFound(id);
        }
        return companyNotFound(companyId);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchCompanyById(@PathVariable Long companyId, @PathVariable Long id, @RequestBody Map<String, Object> updates) {
        ResponseEntity<?> response;
        if (companyService.companyExists(companyId)) {
            if (transactionService.transactionExists(companyId, id)) {
                try {
                    response = new ResponseEntity<>(transactionService.patchFinancialTransactionById(companyId, id, updates), HttpStatus.OK);
                } catch (Exception e) {
                    response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
                }
            } else {
                response = transactionNotFound(id);
            }
        } else {
            response = companyNotFound(companyId);
        }
        return response;
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteTransactionById(@PathVariable Long companyId, @PathVariable Long id) {
        if (companyService.companyExists(companyId)) {
            if (transactionService.transactionExists(companyId, id)) {
                return new ResponseEntity<>(transactionService.deleteFinancialTransactionById(companyId, id), HttpStatus.OK);
            }
            return transactionNotFound(id);
        }
        return companyNotFound(companyId);
    }

    private ResponseEntity<?> companyNotFound(Long companyId) {
        return new ResponseEntity<>("No se encontró la compañía con ID: " + companyId, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<?> transactionNotFound(Long id) {
        return new ResponseEntity<>("No se encontró la transacción con ID: " + id, HttpStatus.NOT_FOUND);
    }*/
}
