package hkmu.comps380f.controller;

import hkmu.comps380f.dao.BlogService;
import hkmu.comps380f.exception.BlogNotFound;
import hkmu.comps380f.exception.PhotoNotFound;
import hkmu.comps380f.model.Blog;
import hkmu.comps380f.model.Photo;
import hkmu.comps380f.view.DownloadingView;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Resource
    private BlogService bService;
    // Controller methods, Form-backing object, ...
    @GetMapping(value = {"", "/list"})
    public String list(ModelMap model) {
        model.addAttribute("blogDatabase", bService.getBlogs());
        model.addAttribute("photoDatabase", bService.getPhotos());
        return "list";
    }
    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("add", "blogForm", new Form());
    }

    public static class Form {
        private String customerName;
        private String subject;
        private String body;
        private List<MultipartFile> photos;

        // Getters and Setters of customerName, subject, body, attachments
        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public List<MultipartFile> getPhotos() {
            return photos;
        }

        public void setPhotos(List<MultipartFile> photos) {
            this.photos = photos;
        }
    }
    @PostMapping("/create")
    public View create(Form form) throws IOException {
        long blogId = bService.createBlog(form.getCustomerName(),
                form.getSubject(), form.getBody(), form.getPhotos());
        return new RedirectView("/blog/view/" + blogId, true);
    }
    @GetMapping("/view/{blogId}")
    public String view(@PathVariable("blogId") long blogId,
                       ModelMap model) throws BlogNotFound {
        Blog blog = bService.getBlog(blogId);
        model.addAttribute("blogId", blogId);
        model.addAttribute("blog", blog);
        return "view";
    }
    @GetMapping("/{blogId}/photo/{photo:.+}")
    public View download(@PathVariable("blogId") long blogId,
                         @PathVariable("photo") UUID photoId)
            throws BlogNotFound, PhotoNotFound {
        Photo photo = bService.getPhoto(blogId, photoId);
        return new DownloadingView(photo.getName(),
                photo.getMimeContentType(), photo.getContents());
    }
    @GetMapping("/delete/{blogId}")
    public String deleteBlog(@PathVariable("blogId") long blogId)
            throws BlogNotFound {
        bService.delete(blogId);
        return "redirect:/blog/list";
    }
    @GetMapping("/{blogId}/delete/{photo:.+}")
    public String deletePhoto (@PathVariable("blogId") long blogId,
                                   @PathVariable("photo") UUID photoId)
            throws BlogNotFound, PhotoNotFound {
        bService.deletePhoto(blogId, photoId);
        return "redirect:/blog/view/" + blogId;
    }

    @ExceptionHandler({BlogNotFound.class, PhotoNotFound.class})
    public ModelAndView error(Exception e) {
        return new ModelAndView("error", "message", e.getMessage());
    }


}
