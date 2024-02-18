import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Blog } from '../blog.model';
import { BlogService } from '../services/blog.service';

@Component({
  selector: 'app-sellbody',
  templateUrl: './sellbody.component.html',
  styleUrls: ['./sellbody.component.css']
})
export class SellbodyComponent implements OnInit {
  blogs: Blog[] = [];
  displayedBlogs: Blog[] = [];
  itemsPerPage = 3;
  currentPage = 0;

  constructor(private blogService: BlogService, private router: Router) {}

  ngOnInit(): void {
    this.loadBlogs();
  }

  loadBlogs(): void {
    this.blogService.getAllBlogs().subscribe(blogs => {
      this.blogs = blogs.map(blog => ({ ...blog, expanded: false }));
      this.updateDisplayedBlogs();
    });
  }

  updateDisplayedBlogs(): void {
    const startIndex = this.currentPage * this.itemsPerPage;
    this.displayedBlogs = this.blogs.slice(startIndex, startIndex + this.itemsPerPage);
  }

  toggleExpanded(index: number): void {
    this.blogs[index].expanded = !this.blogs[index].expanded;
  }

  isExpanded(index: number): boolean {
    return this.blogs[index].expanded;
  }

  previousPage(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.updateDisplayedBlogs();
    }
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.updateDisplayedBlogs();
    }
  }

  get totalPages(): number {
    return Math.ceil(this.blogs.length / this.itemsPerPage);
  }
  
}

