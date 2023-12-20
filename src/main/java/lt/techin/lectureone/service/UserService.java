package lt.techin.lectureone.service;

import lt.techin.lectureone.model.request.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    public User capitalizeName(User user) {

        System.out.println(user);
        return user;
    }


    public static void main(String[] args) {

        List<User> userList = List.of(
                User.builder()
                        .withId(1)
                        .build(),
                User.builder()
                        .withId(2)
                        .build(),
                User.builder()
                        .withId(3)
                        .build());

//        for (User user : userList) {
//            if (user.getId() % 2 == 0) {
//                user.setName("Name" + user.getId());
//            }
//
//            System.out.println(user);
//        }

        List<User> newList = userList.stream()
                .filter((currentUser) -> currentUser.getId() % 2 == 0)
                .peek((currentUser) -> currentUser.setName("Name" + currentUser.getId()))
                .collect(Collectors.toList());

        System.out.println(newList);
    }
}
