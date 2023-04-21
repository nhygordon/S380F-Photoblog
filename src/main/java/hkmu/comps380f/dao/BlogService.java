package hkmu.comps380f.dao;

import hkmu.comps380f.exception.BlogNotFound;
import hkmu.comps380f.exception.PhotoNotFound;
import hkmu.comps380f.model.Blog;
import hkmu.comps380f.model.Photo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class BlogService {
    @Resource
    private BlogRepository bRepo;
    @Resource
    private PhotoRepository pRepo;
    @Transactional
    public List<Blog> getBlogs() {
        return bRepo.findAll();
    }
    @Transactional
    public List<Photo> getPhotos() {
        return pRepo.findAll();
    }
    @Transactional
    public Blog getBlog(long id)
            throws BlogNotFound {
        Blog blog = bRepo.findById(id).orElse(null);
        if (blog == null) {
            throw new BlogNotFound(id);
        }
        return blog;
    }
    @Transactional
    public Photo getPhoto(long blogId, UUID photoId)
            throws BlogNotFound, PhotoNotFound {
        Blog blog = bRepo.findById(blogId).orElse(null);
        if (blog == null) {
            throw new BlogNotFound(blogId);
        }
        Photo photo = pRepo.findById(photoId).orElse(null);
        if (photo == null) {
            throw new PhotoNotFound(photoId);
        }
        return photo;
    }
    @Transactional(rollbackFor = BlogNotFound.class)
    public void delete(long id) throws BlogNotFound {
        Blog deletedBlog = bRepo.findById(id).orElse(null);
        if (deletedBlog == null) {
            throw new BlogNotFound(id);
        }
        bRepo.delete(deletedBlog);
    }
    @Transactional(rollbackFor = PhotoNotFound.class)
    public void deletePhoto(long blogId, UUID photoId)
            throws BlogNotFound, PhotoNotFound {
        Blog blog = bRepo.findById(blogId).orElse(null);
        if (blog == null) {
            throw new BlogNotFound(blogId);
        }
        for (Photo photo : blog.getPhotos()) {
            if (photo.getId().equals(photoId)) {
                blog.deletePhoto(photo);
                bRepo.save(blog);
                return;
            }
        }
        throw new PhotoNotFound(photoId);
    }
    @Transactional
    public long createBlog(String customerName, String subject,
                           String body, List<MultipartFile> attachments, String comment)
            throws IOException {
        Blog blog = new Blog();
        blog.setCustomerName(customerName);
        blog.setSubject(subject);
        blog.setBody(body);
        for (MultipartFile filePart : attachments) {
            Photo photo = new Photo();
            photo.setName(filePart.getOriginalFilename());
            photo.setMimeContentType(filePart.getContentType());
            photo.setContents(filePart.getBytes());
            photo.setBlog(blog);
            if (photo.getName() != null && photo.getName().length() > 0
                    && photo.getContents() != null
                    && photo.getContents().length > 0) {
                blog.getPhotos().add(photo);
            }
        }
        Blog savedBlog = bRepo.save(blog);
        return savedBlog.getId();
    }
    @Transactional(rollbackFor = BlogNotFound.class)
    public void updateBlog(long id, String subject,
                           String body, List<MultipartFile> attachments, String comment)
            throws IOException, BlogNotFound {
        Blog updatedBlog = bRepo.findById(id).orElse(null);
        if (updatedBlog == null) {
            throw new BlogNotFound(id);
        }
        updatedBlog.setSubject(subject);
        updatedBlog.setBody(body);
        for (MultipartFile filePart : attachments) {
            Photo photo = new Photo();
            photo.setName(filePart.getOriginalFilename());
            photo.setMimeContentType(filePart.getContentType());
            photo.setContents(filePart.getBytes());
            photo.setBlog(updatedBlog);
            if (photo.getName() != null && photo.getName().length() > 0
                    && photo.getContents() != null
                    && photo.getContents().length > 0) {
                updatedBlog.getPhotos().add(photo);
            }
        }
        bRepo.save(updatedBlog);
    }

    }
