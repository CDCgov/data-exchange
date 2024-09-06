# DEX (Data Exchange) Product Documentation

Welcome to the DEX Products Documentation repository! 

## Documentation

The documentation for the DEX Products is located in the `docs/` directory of this repository. The documentation is served as a static website via GitHub Pages, allowing for easy access to it online.

### Accessing the Documentation

You can view the DEX products documentation by visiting the following URL:

[https://cdcgov.github.io/data-exchange](https://cdcgov.github.io/data-exchange)

Alternatively, you can navigate to the `docs/` directory in this repository to view the documentation in markdown format.

## Setup for GitHub Pages

This repository is configured to serve the content of the `docs/` directory via GitHub Pages. The following is configured for GitHub Pages:

1. **Go to the repository settings:**
   - Navigate to the GitHub repository's main page.
   - Click on the "Settings" tab.

2. **Scroll to the GitHub Pages section:**
   - Under the "Code and automation" section in the left sidebar, click on "Pages".

3. **Configure the source branch:**
   - In the "Source" dropdown menu, select the `main` branch.
   - In the "Folder" dropdown menu, select `/docs`.

4. **Save your changes:**
   - Click the "Save" button to apply the changes.

The documentation will now be available via GitHub Pages at the URL mentioned above.


## Contributing to Documentation

Please contribute to improve the documentation under the `/docs` directory. Feel free to submit pull requests or open issues with any suggestions, additions or changes.


## Running Documentation Site Locally with Docker Compose

To preview the documentation site locally during development, you can use Docker Compose to build and run the site.

### Prerequisites

- Ensure that you have [Docker](https://docs.docker.com/get-docker/) and [Docker Compose](https://docs.docker.com/compose/install/) installed on your machine.

### Steps to Run the Site Locally

1. **Clone the repository:**
    - Clone the repository using your usual method and checkout your development branch.

2. **Build and run the Docker container:**

    - Run the following command to build and start the Jekyll site inside a Docker container:
        ```
        docker-compose up
        ```
    - **NOTE:** If having issues with errors related to Gem packages missing or certificate related erorrs, you may need to **temporarily disable Zscaler** on first use and start back after packages are installed.

        This will:
        - Build the Jekyll site.
        - Serve the site locally at http://localhost:4000.


3. **Monitoring Changes:**
    - Once the site is running, you can make changes to your files. The site will automatically rebuild and refresh when changes are detected, and the updated version will be visible at http://localhost:4000. Refresh pages in your browser to see your changes!

4. **Stopping the Docker Container:**
    - When you're done, you can stop the Docker container with:
    ```
    docker-compose down
    ```

By following these steps, you'll be able to run your documentation site locally and monitor any changes at http://localhost:4000.

  
## Public Domain Standard Notice
This repository constitutes a work of the United States Government and is not
subject to domestic copyright protection under 17 USC § 105. This repository is in
the public domain within the United States, and copyright and related rights in
the work worldwide are waived through the [CC0 1.0 Universal public domain dedication](https://creativecommons.org/publicdomain/zero/1.0/).
All contributions to this repository will be released under the CC0 dedication. By
submitting a pull request you are agreeing to comply with this waiver of
copyright interest.

## License Standard Notice
The repository utilizes code licensed under the terms of the Apache Software
License and therefore is licensed under ASL v2 or later.

This source code in this repository is free: you can redistribute it and/or modify it under
the terms of the Apache Software License version 2, or (at your option) any
later version.

This source code in this repository is distributed in the hope that it will be useful, but WITHOUT ANY
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
PARTICULAR PURPOSE. See the Apache Software License for more details.

You should have received a copy of the Apache Software License along with this
program. If not, see http://www.apache.org/licenses/LICENSE-2.0.html

The source code forked from other open source projects will inherit its license.

## Privacy Standard Notice
This repository contains only non-sensitive, publicly available data and
information. All material and community participation is covered by the
[Disclaimer](https://github.com/CDCgov/template/blob/master/DISCLAIMER.md)
and [Code of Conduct](https://github.com/CDCgov/template/blob/master/code-of-conduct.md).
For more information about CDC's privacy policy, please visit [http://www.cdc.gov/other/privacy.html](https://www.cdc.gov/other/privacy.html).

## Contributing Standard Notice
Anyone is encouraged to contribute to the repository by [forking](https://help.github.com/articles/fork-a-repo)
and submitting a pull request. (If you are new to GitHub, you might start with a
[basic tutorial](https://help.github.com/articles/set-up-git).) By contributing
to this project, you grant a world-wide, royalty-free, perpetual, irrevocable,
non-exclusive, transferable license to all users under the terms of the
[Apache Software License v2](http://www.apache.org/licenses/LICENSE-2.0.html) or
later.

All comments, messages, pull requests, and other submissions received through
CDC including this GitHub page may be subject to applicable federal law, including but not limited to the Federal Records Act, and may be archived. Learn more at [http://www.cdc.gov/other/privacy.html](http://www.cdc.gov/other/privacy.html).

## Records Management Standard Notice
This repository is not a source of government records, but is a copy to increase
collaboration and collaborative potential. All government records will be
published through the [CDC web site](http://www.cdc.gov).

## Additional Standard Notices
Please refer to [CDC's Template Repository](https://github.com/CDCgov/template)
for more information about [contributing to this repository](https://github.com/CDCgov/template/blob/master/CONTRIBUTING.md),
[public domain notices and disclaimers](https://github.com/CDCgov/template/blob/master/DISCLAIMER.md),
and [code of conduct](https://github.com/CDCgov/template/blob/master/code-of-conduct.md).
