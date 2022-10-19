package br.dp.web.controller;

import br.dp.model.UsersArquives;
import br.dp.model.Usuario;
import br.dp.web.security.provider.DpAuthenticationProvider;
import br.dp.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.nio.file.Files;
import java.nio.file.Path;

@ControllerAdvice
public class LayoutWebController {

    public static final String USER_DEFAULT_IMG = "/resources/images/users/user-default.png";
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/images/users/";

    @Autowired
    private DpAuthenticationProvider authProvider;

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void setUserData(final Model model) {

        Usuario user = null;
        try {
            user = authProvider.getAuthenticatedUser();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        if (user != null) {

            final UsersArquives userImg = userService.loadUserImg(user.getId());

            String pathName = "";

            if (userImg.getPath() != null && !userImg.getPath().isEmpty()) {
                if (!Files.exists(Path.of(System.getProperty("user.dir") + "/images/users/" + userImg.getPath()))) {
                    pathName = USER_DEFAULT_IMG;
                } else {
                    pathName = "/images/users/" + userImg.getPath();
                }
            } else {
                pathName = USER_DEFAULT_IMG;
            }

            model.addAttribute("user", user);
            model.addAttribute("img", pathName);
        }


    }


}
