import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { BlogService } from '../services/blog.service';
import { Router } from '@angular/router';
import { Blog } from '../blog.model';

@Component({
  selector: 'app-blog-form',
  templateUrl: './blog-form.component.html',
  styleUrls: ['./blog-form.component.css']
})
export class BlogFormComponent implements OnInit {
  @Input() blog: Blog = {
    expanded: false // Initialize the expanded property here
  };
  @Output() blogAdded = new EventEmitter<Blog>();

  isUpdate: boolean = false;

  constructor(private blogService: BlogService, private router: Router) {}

  ngOnInit(): void {
    this.isUpdate = !!this.blog.blogId;
  }

  clearForm(): void {
    this.blog = {
      ...this.blog,
      expanded: false // Reset the expanded property when clearing the form
    };
  }

  onSubmit(): void {
    if (this.isUpdate) {
      // Update existing blog
      this.blogService.updateBlog(this.blog).subscribe(response => {
        console.log('Blog updated:', response);
        this.clearForm();
        this.blogAdded.emit({ ...this.blog }); // Emit a copy of the blog
        this.router.navigate(['/sell']);
      });
    } else {
      // Add new blog
      this.blogService.addBlog(this.blog).subscribe(response => {
        console.log('Blog added:', response);
        this.clearForm();
        this.blogAdded.emit({ ...this.blog }); // Emit a copy of the blog
        this.router.navigate(['/sell']);
      });
    }
  }
}
