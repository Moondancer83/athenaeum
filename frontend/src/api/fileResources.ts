import BaseResources, { ResourceEventTarget } from "./BaseResources";

interface DocumentData {
  id: number;
  data: any;
  modifiedAt: string;
  name: string;
  owner: string;
  size: number;
}

interface DocumentListResponse {
  page: {
    size: number;
    totalElements: number;
    totalPages: number;
    number: number;
  };
  _embedded: {
    documents: DocumentData[];
  };
  _links: any;
}

class FileResources extends BaseResources {
  public list(callback?: (value: DocumentListResponse) => void): void {
    const onLoadEnd = (e: ProgressEvent<EventTarget>) => {
      if (callback) {
        const target = e.target as ResourceEventTarget;
        callback(JSON.parse(target.response));
      }
    };

    FileResources.call("GET", "/api/documents", onLoadEnd);
  }
}

export default new FileResources();
