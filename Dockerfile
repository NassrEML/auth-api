# Use the official PostgreSQL 16 image from Docker Hub
FROM postgres:16

# Set the environment variables for the default database
# These values can be changed according to your needs
ENV POSTGRES_DB=users
ENV POSTGRES_USER=root
ENV POSTGRES_PASSWORD=root

# Expose the PostgreSQL port
EXPOSE 5432

# Set the default command for the container
# This command starts the PostgreSQL server
CMD ["postgres"]
