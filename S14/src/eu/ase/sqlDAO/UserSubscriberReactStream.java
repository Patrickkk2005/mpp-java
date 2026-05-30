package eu.ase.sqlDAO;

import eu.ase.iojson.User;

import java.sql.SQLException;
import java.util.concurrent.Flow;

public class UserSubscriberReactStream implements Flow.Subscriber<User> {
    private SqlDao sqlDAO;
    private Flow.Subscription subscription;

    public  UserSubscriberReactStream() throws SQLException, ClassNotFoundException {
        super();
        this.sqlDAO = SqlDao.getInstance();
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("onSubscribe = new subscription" + subscription);
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(User item) {
        System.out.println("onNext = user received: " + item);
        try {
            sqlDAO.InsertIntoDBTable(item.getId(), item.getName(), item.getEmail(), item.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.printf("error occured fetching user: %s" + throwable.getMessage());
        throwable.printStackTrace(System.err);
    }

    @Override
    public void onComplete() {
        System.out.println("fetching user completed");
    }
}
