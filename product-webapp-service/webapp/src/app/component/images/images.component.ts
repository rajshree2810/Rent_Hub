import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-images',
  templateUrl: './images.component.html',
  styleUrls: ['./images.component.css']
})
export class ImagesComponent implements OnInit {

  constructor() { }

  @Input() image:any;
  retrieveImage:any
  ngOnInit(): void {
    this.retrieveImage='data:image/jpg;base64,'+this.image;
  }
}
