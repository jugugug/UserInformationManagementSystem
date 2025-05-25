package com.ch.ch20.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.ch20.entity.Card;
import com.ch.ch20.entity.User;
import com.ch.ch20.mapper.CardMapper;
import com.ch.ch20.util.MyUtil;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements CardService{
    @Override
    public String selectAllCards(int currentPage, Model model, HttpSession session) {
        User user = (User) session.getAttribute("userLogin");
        //5为每页的大小
        IPage<Card> iPage=new Page<>(currentPage,5);
        //条件构造器
        QueryWrapper<Card> wrapper =new QueryWrapper<>();
        wrapper.eq("userId",user.getId());
        //分页查询
        IPage<Card> page=page(iPage,wrapper);
        model.addAttribute("allCards",page.getRecords());
        model.addAttribute("totalPages",page.getPages());
        model.addAttribute("currentPage",currentPage);
        return "main";
    }
    @Override
    public String add(Card card, HttpServletRequest request, String act, HttpSession session) {
        MultipartFile myfile=card.getLogo();
        String rs=null;
        //如果选择了上传文件，将文件上传到指定的目录static/image
        if(!myfile.isEmpty()){
            //生产环境，服务器上
            // String path =request.getServletContext().getRealPath("static/images");
            // 开发环境，工作空间
            String path = "D:\\myproject\\BusinessCardManagementSystem\\ch20\\src\\main\\resources\\static\\images";
            //获得上传文件的原名
            String fileName = myfile.getOriginalFilename ();
            //对文件重命名
            String fileNewName = MyUtil.getNewFileName(fileName);
            File filePath=new File(path+ File.separator+fileNewName);
            //如果文件目录不存在，创建目录
            if (!filePath.getParentFile().exists()){
                filePath.getParentFile().mkdirs();
            }
            //将上传的文件保存到一个目标文件中
            try {
                myfile.transferTo(filePath);
            }catch (IOException e){
                throw new RuntimeException(e);
            }
            //将重命名后的图片名存到card中，添加时使用
            card.setLogoName(fileNewName);
        }
        if ("add".equals(act)){
            User user=(User)session.getAttribute("userLogin");
            card.setUserId(user.getId());
            boolean result = save(card);
            if(result)//成功
                rs = "redirect:/card/selectAllCards?currentPage=1";
            else
                rs = "add";
        }else {//修改
            boolean result = updateById(card);
            if(result)
                rs = "redirect:/card/selectAllCards?currentPage=1";
            else
                rs = "update";
        }
        return rs;
    }
    @Override
    public String detail(Integer id,String act,Model model) {
        model.addAttribute("card",getById(id));
        if("update".equals(act)){
            return "update";
        }
        return "detail";
    }
    @Override
    public String delete(Integer id){
        boolean result = removeById(id);
        if(result)
            return "/card/selectAllCards?currentPage=1";
        return "no";
    }

}
