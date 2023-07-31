package com.example.demo.controller;


import com.example.demo.model.NhanVien;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
public class TestController {
    @GetMapping("/testStreamApi")
    public ResponseEntity<List<NhanVien>> test( ) {
        List<NhanVien> list=new ArrayList<>();
        Random random = new Random();
        int soluong = random.nextInt(10)+10;
        for(int i =0;i<soluong;i++){
            list.add(new NhanVien(randomTen(),(i+1)*10000*(random.nextInt(3)+1),i+1+random.nextInt(20)));
        }
        // liet ke ten nhan vien va luong
        System.out.println("---Ten nhan vien va luong---");
        list.stream().forEach(s->System.out.println("Nhan vien "+s.getName()+" nhan luong "+s.getLuong()));

        //get Ten Viet Hoa
        System.out.println("---Ten nhan vien viet hoa--");
        list.stream().map(nv -> nv.getName().toUpperCase()).forEach(name -> System.out.println(name));

        //tao list ten nhan vien
        List<String> list1 = list.stream().map(nv -> nv.getName()).collect(Collectors.toList());
        System.out.println(list1.toString());

        //lay list nhan vien luong > 50000
        System.out.println("---List nhan vien luong > 200000--");
        List<NhanVien> list2 = list.stream().filter(nv -> nv.getLuong() > 200000).collect(Collectors.toList());
        list.stream().filter(nv -> nv.getLuong() > 200000).forEach(s->System.out.println("Nhan vien "+s.getName()+" nhan luong "+s.getLuong()));



        //kiem tra xem co nhan vien nao luonhg >200000 ten co chu Duy
        boolean bool1 = list.stream().filter(nv -> nv.getLuong() > 200000).anyMatch(nv -> nv.getName().contains("Duy"));
        //kiem tra xem tat ca nv ho nguyen luong tren 50000 k
        boolean bool2 = list.stream().filter(nv -> nv.getName().contains("Nguyễn")).allMatch(nv -> nv.getLuong()>50000);
        boolean bool3 = list.stream().noneMatch(nv -> nv.getLuong() > 1000000);
        System.out.println("bool1: "+bool1+" bool2: "+bool2+" bool3: "+bool3);
        System.out.println("---danh sach nhan vien theo thu tu luong giam dan--");
        list.stream().sorted((nv1,nv2) -> nv2.getLuong()-nv1.getLuong()).forEach(s->System.out.println("Nhan vien "+s.getName()+" nhan luong "+s.getLuong()));

        System.out.println("---danh sach nhan vien theo thu tu luong giam dan--");

        list.stream().sorted(Comparator.comparing(NhanVien::getLuong).thenComparing(NhanVien::getAge).reversed())
                .forEach(s->System.out.println("Nhan vien "+s.getName()+" tuoi "+s.getAge()+" nhan luong "+s.getLuong()));

        int tongLuong = list.stream().map( nv-> nv.getLuong()).reduce(0,(a,b)->(a+b));
        int tongLuong1 = list.stream().mapToInt(nv -> nv.getLuong()).sum();
        System.out.println("Tong Luong phai tra la "+tongLuong1+" so luong "+list.stream().count());
        System.out.println("Tong Luong phai tra la "+tongLuong+" so luong "+list.stream().count());


        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    private String randomTen(){
        String[] ho = {"Nguyễn","Trần","Lê","Hoàng","Phạm"};
        String[] dem = {"Văn","Thị"};
        String[] ten = {"Duy","Khánh","Quang","Dũng","Hiếu"};
        Random random = new Random();
        String randomName = ho[random.nextInt(ho.length)] +" " + dem[random.nextInt(dem.length)]+ " " + ten[random.nextInt(ten.length)];
        return randomName;
    }

}
