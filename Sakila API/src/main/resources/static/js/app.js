
let app = Vue.createApp({
    data() {
        return {
            title: "Sakila API",
            resourceData: {},
            resourceType: "",
            headings: [],
            content: [],
            totalElements: 0,
            totalPages: 0,
            pageSize: 10,
            pageNumber: 0,
            // Added from your previous 'data' definition
            actors: [],
            message: "Hello, Vue!"
        };
    },
    methods: {
        async fetchResource(resource, page = 0, pageSize = this.pageSize) {
            this.resourceType = resource;
            console.log(`Starting fetch for ${resource} with page ${page} and pageSize ${pageSize}.`);
            
            try {
                const url = `http://localhost:8080/api/${resource}?page=${page}&size=${pageSize}`;
                console.log(`Fetching from URL: ${url}`);
                
                let response = await fetch(url);
                if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
                
                let json = await response.json();
                console.log(`Data received for ${resource}:`, json);
                
                this.content = json.content;
                if (json.content && json.content.length > 0) {
                    // Dynamically set headings based on keys of the first item in the content array
                    this.headings = Object.keys(json.content[0]);
                } else {
                    this.headings = [];
                }
                this.totalElements = json.totalElements;
                this.totalPages = json.totalPages;
                this.pageSize = json.size;
                this.pageNumber = json.number;
                console.log(json);
            } catch (error) {
                console.error("Error fetching data:", error);
                alert("Error fetching data. Check console for details.");
            }
        },
        changePage(direction) {
            if (direction === 'next' && this.pageNumber < this.totalPages - 1) {
                this.pageNumber++;
            } else if (direction === 'prev' && this.pageNumber > 0) {
                this.pageNumber--;
            }
            this.fetchResource(this.resourceType, this.pageNumber, this.pageSize);
        },
        fetchFirstPage() {
            this.pageNumber = 0;
            this.fetchResource(this.resourceType, this.pageNumber, this.pageSize);
        },
        fetchLastPage() {
            // Assuming totalPages is correctly updated from fetchResource
            this.pageNumber = this.totalPages - 1;
            this.fetchResource(this.resourceType, this.pageNumber, this.pageSize);
        },
        fetchNextPage() {
            if (this.pageNumber < this.totalPages - 1) {
                this.pageNumber++;
                this.fetchResource(this.resourceType, this.pageNumber, this.pageSize);
            }
        },
        fetchPreviousPage() {
            if (this.pageNumber > 0) {
                this.pageNumber -= 1;
                this.fetchResource(this.resourceType, this.pageNumber, this.pageSize);
            }
        },
        formatHeading(heading) {
            // Split the string at each uppercase letter and replace camelCase with spaces
            const spaced = heading.replace(/([A-Z])/g, ' $1');
            // Convert the first character of each word to uppercase
            const capitalized = spaced.replace(/^./, function(str){ return str.toUpperCase(); });
            return capitalized;
        },
        changePageSize() {
            this.fetchResource(this.resourceType, 0, this.pageSize); // Assuming zero-based page index
        },
    },
    mounted() {
        this.fetchResource('actors'); // Fetch initial data
    },
});
app.mount("#app");
