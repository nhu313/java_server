Java Server
===========

A simple server class written in Java. This is an exercise to learn how the web work. 

# Run: 
  ant run
  
  Once ant finished building the project, you can explore server on http://localhost:5000

  - The server can serve text and images. You can see an example of that on the main page `/`. 
  - If you access an unknown page, the server will return a blank page with status code 404.
  - `/redirect` - redirects you to the main page `/`
  - `/logs` - demonstrates authentication. You can only view the logs if you send username `admin` and `hunter2` through basic auth.
  - `/parameters` - demonstrates how to read params from an URL (`http://localhost:5000/parameters?name=nhu`)


# Test
Tests files are located in test directory. It is written the JUnit framework.
    
