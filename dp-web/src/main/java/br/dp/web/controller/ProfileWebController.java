package br.dp.web.controller;

import br.dp.model.Instituicao;
import br.dp.model.Municipio;
import br.dp.model.Usuario;
import br.dp.web.security.provider.DpAuthenticationProvider;
import br.dp.web.service.CityService;
import br.dp.web.service.FileService;
import br.dp.web.service.InstitutionService;
import br.dp.web.service.UserService;
import br.dp.web.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.AuthProvider;

@Controller
@RequestMapping("/perfil")
public class ProfileWebController {

    public static String message = "";

    @Autowired
    private DpAuthenticationProvider authProvider;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private CityService cityService;

    @GetMapping("/detalhes")
    public String getProfileDetailPage(Model model) {

        Usuario user = authProvider.getAuthenticatedUser();
        String file = fileService.downloadUserFile(user.getId());
        user.setProfileImg(file != null ? file : Constants.USER_DEFAULT_IMG);

        if (!message.equals("")) {
            if (message.contains("sucesso")) {
                model.addAttribute("succesMessage", message);
            } else {
                model.addAttribute("errorMessage", message);
            }
            message = "";
        }
        if(user.getTipo().equals("ADMIN")) {
            model.addAttribute("usuario", user);
            return "profile/profile-detail-page";
        } else {
            String img = fileService.downloadUserFile(user.getId());
            Instituicao institution = institutionService.readById(user.getId());
            final Municipio city = cityService.readById(institution.getMunicipioId());

            model.addAttribute("instituicao",institution);
            model.addAttribute("img", img);
            model.addAttribute("cidade", city);

            return "institutions/institution-detail-page";
        }
    }

    @GetMapping("/editar-perfil")
    public String getEditProfilePage(Model model) {

        Usuario user = authProvider.getAuthenticatedUser();

        model.addAttribute("user", user);
        return "profile/profile-edit-page";
    }

    @PostMapping("/alterar-senha")
    public String changePassword(@RequestParam(value = "password") final String senha, Usuario user) {

        user.setSenha(senha);

        final boolean response = userService.updatePassword(user);

        if (response) {
            message = "Senha alterada com sucesso!";
        } else {
            message = "Não foi possível alterar a senha. Tente novamente!";
        }
        return "redirect:/perfil/detalhes/";

    }

}
