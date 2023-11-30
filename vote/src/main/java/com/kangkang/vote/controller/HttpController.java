package com.kangkang.vote.controller;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kangkang.vote.modle.User;
import com.kangkang.vote.modle.UserLogin;
import com.kangkang.vote.modle.UserRepo;
import com.kangkang.vote.modle.Vote;
import com.kangkang.vote.modle.VoteForm;
import com.kangkang.vote.modle.VoteRepo;
import com.kangkang.vote.modle.VoteResult;
import com.kangkang.vote.modle.Votekv;



@RestController
public class HttpController {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private VoteRepo voteRepository;

    @RequestMapping(value="/hello", method=RequestMethod.GET)
    public String requestMethodName() {
        return "hello";
    }


   @PostMapping(path="/user/add") // Map ONLY POST Requests
   public @ResponseBody String addNewUser (@RequestBody UserLogin login) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        BeanUtils.copyProperties(login, n);
        n.setVoted(false);
        userRepository.save(n);

        return "Add new user success";
  }

  @GetMapping(path="/user/list")
  public @ResponseBody Iterable<User> getAllUsers() {
    // This returns a JSON or XML with the users
    return userRepository.findAll();
  } 

  @PostMapping(path="/vote/add") // Map ONLY POST Requests
   public @ResponseBody String addNewVote (@RequestBody VoteForm voteForm) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Optional<User> user = userRepository.findById(voteForm.getUserId());

        if ((user.isPresent() == false) || (user.get().getVoted())){
          return "no user found, or voted already";
        }

        Vote vote = new Vote();
        BeanUtils.copyProperties(voteForm, vote);
        voteRepository.save(vote);

        User userupdate = user.get();
        userupdate.setVoted(true);
        userRepository.save(userupdate);

        return "vote success";
  }

  @GetMapping(path="/vote/listall")
  public @ResponseBody Iterable<Vote> getAllVotes() {
    // This returns a JSON or XML with the users
    return voteRepository.findAll();
  }


  @GetMapping(path="/vote/result")
  public @ResponseBody VoteResult getVoteResult() {
    // This returns a JSON or XML with the users
    VoteResult result = new VoteResult();

    Integer sum1 = voteRepository.Sum1Voted();
    Integer sum2 = voteRepository.Sum2Voted();
    Integer sum3 = voteRepository.Sum3Voted(); 

    Integer total = 0;
    if (sum1!=null) {
      total+=sum1;
    }
    if (sum2!=null) {
      total+=sum2;
    }
    if (sum3!=null) {
      total+=sum3;
    }

    Float p1 = 0.0f;
    Float p2 = 0.0f;
    Float p3 = 0.0f;

    if (sum1!=null) {
      p1=sum1.floatValue()/total.floatValue();
    }
    if (sum2!=null) {
      p2=sum2.floatValue()/total.floatValue();
    }
    if (sum3!=null) {
      p3=sum3.floatValue()/total.floatValue();
    }

    Votekv k1 = new Votekv();
    k1.setNum(sum1);
    k1.setPercent(p1);

    Votekv k2 = new Votekv();
    k2.setNum(sum2);
    k2.setPercent(p2);

    Votekv k3 = new Votekv();
    k3.setNum(sum3);
    k3.setPercent(p3);

    result.setSpiderMan1(k1);
    result.setSpiderMan2(k2);
    result.setSpiderMan3(k3);

    return result;
  }

}

