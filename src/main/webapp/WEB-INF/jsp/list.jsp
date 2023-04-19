<!DOCTYPE html>
<!-- saved from url=(0083)file:///C:/Users/tgord/Desktop/holopics/Dashboard%20Template%20for%20Bootstrap.html -->
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://getbootstrap.com/docs/4.0/assets/img/favicons/favicon.ico">

    <title>PhotoBlog</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/dashboard/">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


    <!-- Custom styles for this template -->


</head>

<body data-new-gr-c-s-check-loaded="14.1105.0" data-gr-ext-installed="">
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0"
       href="https://getbootstrap.com/docs/4.0/examples/dashboard/#">PhotoBlog</a>

    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="https://getbootstrap.com/docs/4.0/examples/dashboard/#">Sign out</a>
        </li>
    </ul>
</nav>

<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link active" href="/Photoblog/blog/list">
                            Discover (All Photo)
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="">
                            My Photo
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/Photoblog/blog/create">
                            Post Photo
                        </a>
                    </li>
                </ul>
                </ul>
            </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="container">
                <h2>All Photos</h2>
                <c:choose>
                <c:when test="${fn:length(blogDatabase) == 0}">
                <i>There are no blogs in the system.</i>
                </c:when>
                <c:otherwise>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row">
                            <c:forEach items="${photoDatabase}" var="entry2">
                                <div class="col-xxl-6 col-md-6 mx-auto w-100 h-100 align-item-stretch">
                                    <div class="card">
                                        <img class="card-img-top"
                                             src="<c:url value="/blog/${entry2.blogId}/photo/${entry2.id}" />">
                                        <div class="card-body">
                                            <h5 class="card-title">${entry2.name}</h5>
                                            <br><br>
                                            <a class="btn btn-primary"
                                               href="<c:url value="/blog/delete/${entry2.blogId}" />">Delete</a>
                                            <a class="btn btn-primary"
                                               href="<c:url value="/blog/view/${entry2.blogId}" />">Go in and see more
                                            </a>
                                        </div>
                                    </div>

                                </div>


                            </c:forEach>
                        </div>

                    </div>
                </div>
                </c:otherwise>
                </c:choose>
        </main>
    </div>
</div>


</body>
<style>
    body {
        font-size: .875rem;
    }

    .feather {
        width: 16px;
        height: 16px;
        vertical-align: text-bottom;
    }

    /*
     * Sidebar
     */

    .sidebar {
        position: fixed;
        top: 0;
        bottom: 0;
        left: 0;
        z-index: 100; /* Behind the navbar */
        padding: 0;
        box-shadow: inset -1px 0 0 rgba(0, 0, 0, .1);
    }

    .sidebar-sticky {
        position: -webkit-sticky;
        position: sticky;
        top: 48px; /* Height of navbar */
        height: calc(100vh - 48px);
        padding-top: .5rem;
        overflow-x: hidden;
        overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
    }

    .sidebar .nav-link {
        font-weight: 500;
        color: #333;
    }

    .sidebar .nav-link .feather {
        margin-right: 4px;
        color: #999;
    }

    .sidebar .nav-link.active {
        color: #007bff;
    }

    .sidebar .nav-link:hover .feather,
    .sidebar .nav-link.active .feather {
        color: inherit;
    }

    .sidebar-heading {
        font-size: .75rem;
        text-transform: uppercase;
    }

    /*
     * Navbar
     */

    .navbar-brand {
        padding-top: .75rem;
        padding-bottom: .75rem;
        font-size: 1rem;
        background-color: rgba(0, 0, 0, .25);
        box-shadow: inset -1px 0 0 rgba(0, 0, 0, .25);
    }

    .navbar .form-control {
        padding: .75rem 1rem;
        border-width: 0;
        border-radius: 0;
    }

    .form-control-dark {
        color: #fff;
        background-color: rgba(255, 255, 255, .1);
        border-color: rgba(255, 255, 255, .1);
    }

    .form-control-dark:focus {
        border-color: transparent;
        box-shadow: 0 0 0 3px rgba(255, 255, 255, .25);
    }

    /*
     * Utilities
     */

    .border-top {
        border-top: 1px solid #e5e5e5;
    }

    .border-bottom {
        border-bottom: 1px solid #e5e5e5;
    }

</style>
</html>
