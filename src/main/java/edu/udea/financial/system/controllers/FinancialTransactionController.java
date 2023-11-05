package edu.udea.financial.system.controllers;

import edu.udea.financial.system.entities.FinancialTransaction;
import edu.udea.financial.system.services.FinancialTransactionService;
import edu.udea.financial.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;

@Controller
@RequestMapping("/{userId}/enterprises/{companyId}/movements")
public class FinancialTransactionController {

    @Autowired
    private FinancialTransactionService transactionService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String getFinancialTransactions(@AuthenticationPrincipal OidcUser profile, @PathVariable Long userId, @PathVariable Long companyId, Model model) {
        model.addAttribute("userId",  userId);
        model.addAttribute("companyId", companyId);
        model.addAttribute("role", userService.verifyRoleByEmail(profile));
        model.addAttribute("transactions", transactionService.getFinancialTransactions(companyId));
        return "transactionsView";
    }
    @GetMapping("/create")
    public String createFinancialTransaction(@AuthenticationPrincipal OidcUser profile, @PathVariable Long userId, @PathVariable Long companyId, Model model) {
        model.addAttribute("userId",  userId);
        model.addAttribute("companyId", companyId);
        model.addAttribute("role", userService.verifyRoleByEmail(profile));
        model.addAttribute("newTransaction", new FinancialTransaction());
        return "createTransactionView";
    }

    @PostMapping("/create")
    public RedirectView createFinancialTransaction(@AuthenticationPrincipal OidcUser profile, @PathVariable Long userId, @PathVariable Long companyId, @ModelAttribute FinancialTransaction transaction) {
        transactionService.createFinancialTransaction(userService.getUserByEmail(profile.getEmail()).getId(), companyId, transaction);
        return new RedirectView("/" + userId + "/enterprises/" + companyId + "/movements");
    }

    @GetMapping("/{id}/update")
    public String updateFinancialTransaction(@AuthenticationPrincipal OidcUser profile, @PathVariable Long userId, @PathVariable Long companyId, @PathVariable Long id, Model model) {
        model.addAttribute("userId",  userId);
        model.addAttribute("companyId", companyId);
        model.addAttribute("role", userService.verifyRoleByEmail(profile));
        model.addAttribute("transaction", transactionService.getTransactionById(id));
        return "updateTransactionView";
    }

    @PutMapping("/{id}/update")
    public RedirectView updateFinancialTransaction(@PathVariable Long userId, @PathVariable Long companyId, @ModelAttribute FinancialTransaction transaction) {
        transactionService.updateTransaction(transaction);
        return new RedirectView("/" + userId + "/enterprises/" + companyId + "/movements");
    }

    @DeleteMapping("/{id}")
    public RedirectView deleteTransactionById(@PathVariable Long userId, @PathVariable Long companyId,  @PathVariable Long id) {
        transactionService.deleteFinancialTransactionById(companyId, id);
        return new RedirectView("/" + userId + "/enterprises/" + companyId + "/movements");
    }

}
