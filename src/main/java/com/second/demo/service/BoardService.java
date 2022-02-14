package com.second.demo.service;

import com.second.demo.entity.board2;
import com.second.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // 글작성
    public void write(board2 board, MultipartFile file) throws Exception {
        //경로설정
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";
        //파일이름생성
        UUID uuid = UUID.randomUUID();
        // 저장될 파일이름 생성
        String fileName = uuid + "_" + file.getOriginalFilename();
        // 파일경로에 파일이름으로 저장하는 객체생성
        File saveFile = new File(projectPath,fileName);

        file.transferTo(saveFile);

        board.setFilename(fileName);
        board.setFilepath("/files/"+fileName);


        boardRepository.save(board);

    }

    // 게시글 리스트 처리
   public Page<board2> boadList(Pageable pageable){
       return boardRepository.findAll(pageable);
   }

   // 특정 게시글 불러오기
    public board2 boardView(Integer id){
        return boardRepository.findById(id).get();
    }


    // 특정 게시글 삭제
    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }

}
