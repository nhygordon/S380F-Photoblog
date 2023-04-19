package hkmu.comps380f.controller;

import hkmu.comps380f.model.Photo;
import hkmu.comps380f.model.blog;
import hkmu.comps380f.view.DownloadingView;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/blog")
public class blogController {
    private volatile long TICKET_ID_SEQUENCE = 1;
    private Map<Long, blog> blogDatabase = new ConcurrentHashMap<>();
    // Controller methods, Form-backing object, ...
    @GetMapping(value = {"", "/list"})
    public String list(ModelMap model) {
        model.addAttribute("blogDatabase", blogDatabase);
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
        blog blog = new blog();
        blog.setId(this.getNextblogId());
        blog.setCustomerName(form.getCustomerName());
        blog.setSubject(form.getSubject());
        blog.setBody(form.getBody());
        for (MultipartFile filePart : form.getPhotos()) {
            Photo photo = new Photo();
            photo.setId(RandomStringUtils.randomAlphanumeric(8));
            photo.setName(filePart.getOriginalFilename());
            photo.setMimeContentType(filePart.getContentType());
            photo.setContents(filePart.getBytes());
            if (photo.getName() != null && photo.getName().length() > 0
                    && photo.getContents() != null && photo.getContents().length > 0)
                blog.addPhoto(photo);
        }
        this.blogDatabase.put(blog.getId(), blog);
        return new RedirectView("/blog/view/" + blog.getId(), true);
    }
    private synchronized long getNextblogId() {
        return this.TICKET_ID_SEQUENCE++;
    }
    @GetMapping("/view/{blogId}")
    public String view(@PathVariable("blogId") long blogId,
                       ModelMap model) {
        blog blog = this.blogDatabase.get(blogId);
        if (blog == null) {
            return "redirect:/blog/list";
        }
        model.addAttribute("blogId", blogId);
        model.addAttribute("blog", blog);
        return "view";
    }
    @GetMapping("/{blogId}/photo/{photo:.+}")
    public View download(@PathVariable("blogId") long blogId,
                         @PathVariable("photo") String PhotoId) {
        blog blog = this.blogDatabase.get(blogId);
        if (blog != null) {
            Photo photo = blog.getPhoto(PhotoId);
            if (photo != null)
                return new DownloadingView(photo.getName(),
                        photo.getMimeContentType(), photo.getContents());
        }
        return new RedirectView("/blog/list", true);
    }
}
