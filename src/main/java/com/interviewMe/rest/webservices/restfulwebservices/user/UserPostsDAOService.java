package com.interviewMe.rest.webservices.restfulwebservices.user;

import com.interviewMe.rest.webservices.restfulwebservices.exceptions.PostNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserPostsDAOService {

    public static Map<Integer, List<UserPost>> listOfPosts = new HashMap<>();
    private static List<UserPost> adamPosts = new ArrayList<>();
    private static List<UserPost> divyaPosts = new ArrayList<>();
    private static List<UserPost> deveshPosts = new ArrayList<>();


    static {
        adamPosts.add(new UserPost(1, "Hi. I am Adam. This is my first post."));
        adamPosts.add(new UserPost(2, "Hi. I am Adam. This is my second post."));
        adamPosts.add(new UserPost(3, "Hi. I am Adam. This is my third post."));

        divyaPosts.add(new UserPost(1, "Hi. I am Divya. This is my first post."));
        divyaPosts.add(new UserPost(2, "Hi. I am Divya. This is my second post."));
        divyaPosts.add(new UserPost(3, "Hi. I am Divya. This is my third post."));
        divyaPosts.add(new UserPost(4, "Hi. I am Divya. This is my fourth post."));

        deveshPosts.add(new UserPost(1, "Hi. I am Devesh. This is my first post."));
        deveshPosts.add(new UserPost(2, "Hi. I am Devesh. This is my second post."));

        listOfPosts.put(1, adamPosts);
        listOfPosts.put(2, divyaPosts);
        listOfPosts.put(3, deveshPosts);
    }

    public UserPostsDAOService() {
    }

    public List<UserPost> findAllPostsOfUser(User user) {
        if (listOfPosts.containsKey(user.getId()))
            return listOfPosts.get(user.getId());
        else {
            return Collections.EMPTY_LIST;
        }
    }

    public User saveNewPostForUser(User user, UserPost userPost) {
        List<UserPost> userPostsList;
        if (listOfPosts.containsKey(user.getId())) {
            userPostsList = listOfPosts.get(user.getId());
            if (userPost.getPostId() == null) {
                int maxPostId = 0;
                for (UserPost userPost1 : userPostsList) {
                    maxPostId = maxPostId > userPost1.getPostId() ? maxPostId : userPost1.getPostId();
                }
                userPost.setPostId(maxPostId + 1);
            }
        } else {
            userPostsList = new ArrayList<>();
            if (userPost.getPostId() == null) {
                userPost.setPostId(1);
            }
        }
        userPostsList.add(userPost);
        user.setUserPosts(userPostsList);
        listOfPosts.put(user.getId(), userPostsList);
        return user;
    }

    public UserPost findPostForUser(int id, int postId) {
        if (listOfPosts.containsKey(id)) {
            List<UserPost> UserPost = listOfPosts.get(id);
            for (UserPost userPost : UserPost) {
                if (userPost.getPostId() == postId) {
                    return userPost;
                }
            }
        }
        throw new PostNotFoundException(String.format("No post %s found for user %s", postId, id));
    }

}
