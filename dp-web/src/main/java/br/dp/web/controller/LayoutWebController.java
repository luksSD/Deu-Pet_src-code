package br.dp.web.controller;

import br.dp.model.UsersArquives;
import br.dp.model.Usuario;
import br.dp.web.security.provider.DpAuthenticationProvider;
import br.dp.web.service.FileService;
import br.dp.web.service.UserService;
import br.dp.web.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.nio.file.Files;
import java.nio.file.Path;

@ControllerAdvice
public class LayoutWebController {


    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/images/users/";

    @Autowired
    private DpAuthenticationProvider authProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @ModelAttribute
    public void setUserData(final Model model) {

        Usuario user = null;
        try {
            user = authProvider.getAuthenticatedUser();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        if (user != null) {
            String userImgPath = fileService.downloadUserFile(user.getId());

            if (userImgPath == null || userImgPath.isEmpty()) {
                userImgPath = Constants.USER_DEFAULT_IMG;
            }

            model.addAttribute("user", user);
            model.addAttribute("profileImg", userImgPath);
        }


    }


}
