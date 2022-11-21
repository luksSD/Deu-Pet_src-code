package br.dp.api.service.impl;

import br.dp.api.service.UserService;
import br.dp.db.dao.UserDao;
import br.dp.model.UsersArquives;
import br.dp.model.Usuario;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AmazonS3Client awsS3Client;

    @Override
    public Usuario validateUsernameAndPassword(final String username, final String password) {
        return null;
    }

    @Override
    public Usuario validateLogin(final String encodedData) {

        final Map<CREDENTIALS, String> credentialsMap = decodeAndGetUserNameAndPassword(encodedData);

        if (credentialsMap == null || credentialsMap.size() != 2) {
            return null;
        }

        final String username = credentialsMap.get(CREDENTIALS.USERNAME);
        final String password = credentialsMap.get(CREDENTIALS.PASSWORD);

        final Usuario user = userDao.validateUsernameAndPassword(username, password);

        if (user == null) {
            return null;
        }

        return user;

    }

    @Override
    public Long create(final Usuario user) {
        return userDao.create(user);
    }

    private enum CREDENTIALS {
        USERNAME,
        PASSWORD
    }

    private Map<CREDENTIALS, String> decodeAndGetUserNameAndPassword(final String encodedData) {

        try {
            final String[] splittedData = encodedData.split("Basic ");

            if (splittedData.length != 2) {
                return null;
            }

            final byte[] decodedBytes = Base64.getDecoder().decode(splittedData[1]);

            final String decodedString = new String(decodedBytes, "UTF-8");

            final String[] firstPart = decodedString.split("Username=");
            if (firstPart.length != 2) {
                return null;
            }

            final String[] credentials = firstPart[1].split(";Password=");
            if (credentials.length != 2) {
                return null;
            }

            final Map<CREDENTIALS, String> credentialsMap = new HashMap<CREDENTIALS, String>();
            credentialsMap.put(CREDENTIALS.USERNAME, credentials[0]);
            credentialsMap.put(CREDENTIALS.PASSWORD, credentials[1]);

            return credentialsMap;

        } catch (final Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
