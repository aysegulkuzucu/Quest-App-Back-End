package com.project.questapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.questapp.entities.User;
import com.project.questapp.repos.CommentRepository;
import com.project.questapp.repos.LikeRepository;
import com.project.questapp.repos.PostRepository;
import com.project.questapp.repos.UserRepository;

@Service
public class UserService {
	
	UserRepository userRepository;
	LikeRepository likeRepository;
	CommentRepository commentRepository;
	PostRepository postRepository;

	public UserService(UserRepository userRepository, LikeRepository likeRepository, 
			CommentRepository commentRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.likeRepository = likeRepository;
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}
	
	public List<User> geltAllUsers() {
		// burada repoya baglanip tum userlari aliriz
		return userRepository.findAll();
	}

	public User saveOneUser(User newUser) {
		// TODO Auto-generated method stub
		return userRepository.save(newUser);
	}

	public User getOneUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	
	public User updateOneUser(Long userId, User newUser) {
		//değiştirilmek istenen useri userid kullanarak bulup user değişkenine atıyoruz
		Optional<User> user = userRepository.findById(userId);
		//user değişkeni databasede varmı diye kontrol ediyoruz
		if(user.isPresent()) {
			//var ise useri founduser değişkenine atıyoruz
			User foundUser = user.get();
			//varolan useri new userin verdiği değişikliklerle yeniden set ediyoruz
			foundUser.setUserName(newUser.getUserName());
			foundUser.setPassword(newUser.getPassword());
			foundUser.setAvatar(newUser.getAvatar());
			//useri kaydediyoruz
			userRepository.save(foundUser); // db'ye save ettik
			//ve düzenlenmiş useri döndürüyoruz
			return foundUser;
		}else  // boyle bir user yoksa
			return null;
	}

	public void deleteById(Long userId) {
		userRepository.deleteById(userId);
	}

	public User getOneUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public List<Object> getUserActivity(Long userId) {
		List<Long> postIds = postRepository.findTopByUserId(userId);
		if(postIds.isEmpty())
			return null;
		List<Object> comments = commentRepository.findUserCommentsByPostId(postIds);
		List<Object> likes = likeRepository.findUserLikesByPostId(postIds);
		List<Object> result = new ArrayList<>();
		result.addAll(comments);
		result.addAll(likes);
		return result;
	}

}
