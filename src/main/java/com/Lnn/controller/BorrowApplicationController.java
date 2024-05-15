package com.Lnn.controller;

import com.Lnn.domain.Result;
import com.Lnn.domain.dto.AddBorrowApplicationDto;
import com.Lnn.domain.dto.UpdateBorrowApplicationDto;
import com.Lnn.service.BorrowApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrow")
public class BorrowApplicationController {


    @Autowired
    private BorrowApplicationService borrowApplicationService;

    //-----------------------------------学生借用相关----------------------------------- //
    @PostMapping("/add")
    public Result addBorrow(@RequestBody AddBorrowApplicationDto addBorrowApplicationDto)
    {
        return borrowApplicationService.add(addBorrowApplicationDto);
    }

    @GetMapping("/getByStudent")
    public Result get(Integer pageNum,Integer pageSize)
    {
        return borrowApplicationService.getByStudent(pageNum,pageSize);
    }
    @PostMapping("/update")
    public Result update(@RequestBody UpdateBorrowApplicationDto updateBorrowApplicationDto)
    {
        return borrowApplicationService.modifyLabBorrow(updateBorrowApplicationDto);
    }

    @PostMapping("/completeBorrow")
    public Result complete(@RequestParam("id") Integer id)
    {
        return borrowApplicationService.complete(id);
    }

}
