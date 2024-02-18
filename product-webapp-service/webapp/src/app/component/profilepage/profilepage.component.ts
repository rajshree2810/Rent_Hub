import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BankDetail } from 'src/app/model/BankDetail';
import { User } from 'src/app/model/User';
import { ProfileService } from 'src/app/service/profile.service';


declare var cloudinary: any; // Declare the cloudinary variable

@Component({
  selector: 'app-profilepage',
  templateUrl: './profilepage.component.html',
  styleUrls: ['./profilepage.component.css']
})
export class ProfilepageComponent{
  profileForm:FormGroup;
  bankForm:FormGroup;
  fileName="";
  userData:User;
  stage2:boolean=false;//For dynamic view to  show banking data
  edit:boolean=false;
  bank:BankDetail


  constructor(private proileservice:ProfileService,private formbuilder:FormBuilder){
    this.userData=new User();
    this.bank=new BankDetail();
    this.getUserData();

    this.profileForm=this.formbuilder.group({
      userId:[this.userData.userId,Validators.required],
      password:['',Validators.required],
      firstName:['',Validators.compose([Validators.required, Validators.pattern("[a-zA-Z]+[a-zA-Z]")])],
      lastName:['',Validators.compose([Validators.required, Validators.pattern("[a-zA-Z]+[a-zA-Z]")])],
      email:['',[Validators.required,Validators.email]],
      phone:['',[Validators.required,Validators.maxLength(13),Validators.minLength(10),Validators.pattern("^((\\+91-?)|0)?[0-9]{10}")]],
      address:['',Validators.required],
      dateofbirth:['',Validators.required],
      gender:['',Validators.required],
    })

    this.bankForm=this.formbuilder.group({
      userId:[''],
      bankName:['',[Validators.required,Validators.pattern('[a-zA-Z][a-zA-Z ]+')]],
      accountNumber:[0,[Validators.required,Validators.pattern("[0-9]+")]],
      ifsc:['',[Validators.required,Validators.pattern('[a-zA-Z0-9]+')]],
      holderName:['',[Validators.required,Validators.pattern('[a-zA-Z][a-zA-Z ]+')]]
    })

  }

  getUserData(){
    return this.proileservice.getUser().subscribe(res=>{
      this.userData=res;
      this.bank=this.userData.bankDetail as BankDetail;
      this.profileForm.setValue(this.userData);
      this.bankForm.setValue(this.userData.bankDetail as BankDetail);
      this.bankForm.get('userId')?.setValue(this.userData.userId);
    })
  }

  saveProfile(profileForm:FormGroup){
    this.proileservice.saveProfile(profileForm.value).subscribe(resp=>{
      this.userData=resp;
      alert("Profile Updated")
      this.edit=false;
    })
  }

  submitBankDetail(bankForm:FormGroup){
    this.proileservice.saveBankProfile(bankForm.value).subscribe(resp=>{
      alert("Data Saved");
      this.edit=false;
      this.getUserData();
    })
  }

  toogleEdit(){
    this.edit=!this.edit;
    this.getUserData();
  }

  togleStage(){
    this.stage2=!this.stage2;
  }



  // uploadProfileImage(event:any){
  //   const file:File = event.target.files[0];

  //   if(file){
  //     this.fileName=file.name;
  //     console.log(this.fileName)
  //     this.proileservice.upload(file).subscribe(event=>{
  //     },err=>{
  //       alert("Error occured in file uploading");
  //     })
  //   }
  // }

  uploadProfileImage(){
    this.proileservice.setProfileImageUrl(this.userData).subscribe(resp=>{
      alert('Data saved');
      this.edit=false;
    })
  
  }

  openWidget() {
    cloudinary.openUploadWidget({
      cloudName: 'dsu3fmn8v',
      uploadPreset: 's98sswr3',
      sources: ['local', 'url'],
      showAdvancedOptions: true,
      cropping: 'server',
      multiple: false,
      defaultSource: 'local',
      styles: {
        palette: {
          window: '#FFFFFF',
          sourceBg: '#FFFFFF',
          windowBorder: '#90a0b3',
          tabIcon: '#0078ff',
          inactiveTabIcon: '#69778A',
          menuIcons: '#0078ff',
          link: '#0078ff',
          action: '#0078ff',
          inProgress: '#0078ff',
          complete: '#0078ff',
          error: '#ff0000',
          textDark: '#000000',
          textLight: '#FFFFFF'
        },
        fonts: {
          default: null,
          "'Poppins', sans-serif": {
            url: 'https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap',
            active: true
          }
        }
      }
    }, (error: any, result: any) => {
      if (!error && result && result.event === 'success') {
        this.userData.imageUrl = result.info.secure_url; // Update product imageUrl
        console.log(this.userData.imageUrl);
        this.uploadProfileImage();
      }
    });
  }
  
}

